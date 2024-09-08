/**
 * Contains custom react hooks
 *
 * Keep in mind that hooks could only be used from functional components!
 */

package ru.posidata.views.utils.internals

import js.objects.jso
import kotlinext.js.assign
import kotlinx.browser.window
import react.*
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import org.w3c.dom.url.URLSearchParams
import org.w3c.fetch.Headers
import org.w3c.fetch.RequestCredentials
import org.w3c.fetch.RequestInit
import org.w3c.fetch.Response

val requestStatusContext: Context<RequestStatusContext?> = createContext()

data class RequestStatusContext(
    val setResponse: StateSetter<Response?>,
    val setRedirectToFallbackView: StateSetter<Boolean>,
)

/**
 * Hook to get callbacks to perform requests in functional components.
 *
 * @param request
 * @return a function to trigger request execution.
 */
fun <R> useDeferredRequest(
    request: suspend WithRequestStatusContext.() -> R,
): () -> Unit {
    val scope = CoroutineScope(Dispatchers.Default)
    val context = useRequestStatusContext()
    val (isSending, setIsSending) = useState(false)
    useEffect(isSending) {
        if (!isSending) {
            return@useEffect
        }
        scope.launch {
            request(context)
            setIsSending(false)
        }.invokeOnCompletion {
            if (it != null && it !is CancellationException) {
                setIsSending(false)
            }
        }
        cleanup {
            if (scope.isActive) {
                scope.cancel()
            }
        }
    }
    val initiateSending: () -> Unit = {
        if (!isSending) {
            setIsSending(true)
        }
    }
    return initiateSending
}

fun <R> useRequest(
    dependencies: Array<dynamic> = emptyArray(),
    request: suspend WithRequestStatusContext.() -> R,
) {
    val scope = CoroutineScope(Dispatchers.Default)
    val context = useRequestStatusContext()

    useEffect(*dependencies) {
        scope.launch {
            request(context)
        }
        cleanup {
            if (scope.isActive) {
                scope.cancel()
            }
        }
    }
}


fun useRequestStatusContext(): WithRequestStatusContext {
    val statusContext = useContext(requestStatusContext)
    val context = object : WithRequestStatusContext {
        override val coroutineScope = CoroutineScope(Dispatchers.Default)
        override fun setResponse(response: Response) {
            statusContext?.run {
                setResponse(response)
            }
        }

        override fun setRedirectToFallbackView(isNeedRedirect: Boolean, response: Response) {
            statusContext?.run {
                setRedirectToFallbackView(
                    isNeedRedirect && response.status == 404.toShort()
                )
            }
        }

        override fun setLoadingCounter(transform: (oldValue: Int) -> Int) {
            statusContext?.run { setLoadingCounter(transform) }
        }
    }
    return context
}

suspend fun <T : Any> WithRequestStatusContext.get(
    url: String,
    params: T = jso { },
    headers: Headers,
    loadingHandler: suspend (suspend () -> Response) -> Response,
    responseHandler: (Response) -> Unit = this::withModalResponseHandler,
): Response = request(
    url = url.withParams(params),
    method = "GET",
    headers = headers,
    loadingHandler = loadingHandler,
)

fun <T : Any> String.withParams(params: T): String {
    val paramString = URLSearchParams(params).toString()

    return when {
        paramString.isEmpty() -> this
        endsWith('?') -> this + paramString
        contains('?') -> "$this&$paramString"
        else -> "$this?$paramString"
    }
}

private fun ComponentWithScope<*, *>.withModalResponseHandler(
    response: Response,
    isNeedRedirect: Boolean
) {
    if (!response.ok) {
        val statusContext: RequestStatusContext = this.asDynamic().context
        statusContext.setRedirectToFallbackView(isNeedRedirect && response.status == 404.toShort())
        statusContext.setResponse.invoke(response)
    }
}

suspend fun request(
    url: String,
    method: String,
    headers: Headers,
    body: dynamic = undefined,
    credentials: RequestCredentials? = undefined,
    loadingHandler: suspend (suspend () -> Response) -> Response,
): Response = loadingHandler {
    window.fetch(
        input = url,
        RequestInit(
            method = method,
            headers = headers,
            body = body,
            credentials = credentials,
        )
    )
        .await()
}

interface WithRequestStatusContext {
    /**
     * Coroutine used for processing [setLoadingCounter]
     */
    val coroutineScope: CoroutineScope

    /**
     * @param response
     */
    fun setResponse(response: Response)

    /**
     * @param isNeedRedirect
     * @param response
     */
    fun setRedirectToFallbackView(isNeedRedirect: Boolean, response: Response)

    /**
     * @param transform
     */
    fun setLoadingCounter(transform: (oldValue: Int) -> Int)
}

fun noopResponseHandler(@Suppress("UNUSED_PARAMETER") response: Response) = Unit

abstract class ComponentWithScope<P : Props, S : State> : CComponent<P, S>() {
    /**
     * A [CoroutineScope] that should be used by implementing classes. Will be cancelled on unmounting.
     */
    val scope: CoroutineScope = CoroutineScope(Dispatchers.Default)

    override fun componentWillUnmount() {
        if (scope.isActive) {
            scope.cancel()
        }
    }
}

abstract class CComponent<P : Props, S : State> : Component<P, S> {
    constructor() : super() {
        state = jso { init() }
    }
    constructor(props: P) : super(props) {
        state = jso { init(props) }
    }

    @Suppress(
        "WRONG_OVERLOADING_FUNCTION_ARGUMENTS",
        "EMPTY_BLOCK_STRUCTURE_ERROR",
        "MISSING_KDOC_CLASS_ELEMENTS",
        "MISSING_KDOC_ON_FUNCTION",
    )
    open fun S.init() {}

    /**
     * @param props
     */
    @Suppress(
        "WRONG_OVERLOADING_FUNCTION_ARGUMENTS",
        "EMPTY_BLOCK_STRUCTURE_ERROR",
        "MISSING_KDOC_CLASS_ELEMENTS"
    )
    open fun S.init(props: P) {}

    /**
     *  Wrapper for convenient use of `ChildrenBuilder#render()`
     */
    override fun render(): ReactNode? = Fragment.create {
        render()
    }

    /**
     * Method that should be overridden in order to render the component
     */
    abstract fun ChildrenBuilder.render()

    /**
     * State setter
     *
     * @param stateSetter lambda to set a state
     */
    fun setState(stateSetter: S.() -> Unit) {
        super.setState({ assign(it, stateSetter) })
    }
}

private fun WithRequestStatusContext.withModalResponseHandler(
    response: Response,
) {
    if (!response.ok) {
        setResponse(response)
    }
}

val jsonHeaders = Headers()
    .withAcceptJson()
    .withContentTypeJson()

fun Headers.withContentTypeJson() = apply {
    set("Content-Type", "application/json")
}

fun Headers.withAcceptJson() = apply {
    set("Accept", "application/json")
}

suspend fun noopLoadingHandler(request: suspend () -> Response) = request()

suspend fun Response.unpackMessageOrNull(): String? = decodeFieldFromJsonStringOrNull("message")

suspend inline fun Response.decodeFieldFromJsonStringOrNull(fieldName: String): String? = text().await()
    .let { Json.parseToJsonElement(it) }
    .let { it as? JsonObject }
    ?.let { it[fieldName] }
    ?.let { it as? JsonPrimitive }
    ?.content

suspend inline fun <reified T> Response.decodeFromJsonString() = Json.decodeFromString<T>(text().await())

