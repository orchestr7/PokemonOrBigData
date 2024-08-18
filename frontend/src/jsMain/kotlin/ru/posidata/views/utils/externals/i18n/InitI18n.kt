/**
 * File containing initialization of i18next
 */

package ru.posidata.views.utils.externals.i18n

/**
 * Function that encapsulates i18n initialization.
 *
 * @param isDebug flag to set debug mode
 * @param interpolationEscapeValue interpolation.escapeValue value
 */
@Suppress("UNUSED_PARAMETER")
fun initI18n(isDebug: Boolean = false, interpolationEscapeValue: Boolean = false) {
    js("""
        var i18n = require("i18next");
        var reactI18n = require("react-i18next");
        var Backend = require("i18next-http-backend");
        
        i18n
            .use(reactI18n.initReactI18next)
            .use(Backend.default)
            .init({
                load: 'languageOnly',
                initImmediate: false,
                partialBundledLanguages: true,
                ns: [
                    'main'
                ],
                backend: {
                    loadPath: '/locales/{{lng}}/{{ns}}.json'
                },
                lng: "en",
                fallbackLng: "en",
                debug: isDebug,
                interpolation: {
                    escapeValue: interpolationEscapeValue
                },
            }); 
    """)
}
