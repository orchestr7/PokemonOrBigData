package ru.posidata.backend.service

import org.apache.commons.codec.digest.HmacUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.security.MessageDigest


@Service
class TelegramAuthService(
    @Value("\${telegram.bot.token}") private val telegramToken: String,
) {
    fun isValidHash(parsedData: Map<String, String>, hash: String): Boolean {
        val dataKeys = parsedData.keys.filter { it != "hash" }.filter { parsedData[it] != "undefined" }.sorted()
        val items = dataKeys.map { key -> "$key=${parsedData[key]}" }
        val dataCheckString = items.joinToString("\n")

        val secretKey: ByteArray = MessageDigest.getInstance("SHA-256").digest(telegramToken.toByteArray())
        val initDataHash: String = HmacUtils("HmacSHA256", secretKey).hmacHex(dataCheckString)

        return initDataHash == hash
    }
}
