package ru.posidata.backend.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.security.MessageDigest
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

@Service
class TelegramAuthService(
    @Value("\${telegram.bot.token}") private val telegramToken: String,
    private val userService: UserService
) {
    fun authenticate(request: Map<String, Any>): AuthResult {
        val hash = request["hash"] as String
        val sortedRequest = request.toMutableMap().apply { remove("hash") }

        val dataCheckString = sortedRequest.entries
            .sortedBy { it.key.lowercase(Locale.getDefault()) }
            .joinToString("\n") { "${it.key}=${it.value}" }

        val secretKey = SecretKeySpec(
            MessageDigest.getInstance("SHA-256").digest(telegramToken.toByteArray(Charsets.UTF_8)),
            "HmacSHA256"
        )
        val mac = Mac.getInstance("HmacSHA256")
        mac.init(secretKey)

        val result = mac.doFinal(dataCheckString.toByteArray(Charsets.UTF_8))
        val calculatedHash = result.joinToString("") { "%02x".format(it) }

        return if (hash.equals(calculatedHash, ignoreCase = true)) {
            val user = userService.findOrCreateUser(request)
            println(user)
            AuthResult(true, "token")
        } else {
            AuthResult(false, null)
        }
    }
}

data class AuthResult(val isAuthenticated: Boolean, val token: String?)