package ru.posidata.backend.service

import org.apache.commons.codec.digest.HmacUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.posidata.common.UserDataFromTelegram
import java.security.MessageDigest


@Service
class TelegramAuthService(
    @Value("\${telegram.bot.token}") private val telegramToken: String,
) {
    fun isValidHash(userDataFromTelegram: UserDataFromTelegram): Boolean {
        println("Received a request to get results from $userDataFromTelegram. Converted to map: ${userDataFromTelegram.convertToMap()}")

        val parsedData = userDataFromTelegram.convertToMap()
        val dataKeys = parsedData.keys.filter { it != "hash" }.filter { parsedData[it] != "undefined" }.sorted()
        val items = dataKeys.map { key -> "$key=${parsedData[key]}" }
        val dataCheckString = items.joinToString("\n")

        val secretKey: ByteArray = MessageDigest.getInstance("SHA-256").digest(telegramToken.toByteArray())
        val initDataHash: String = HmacUtils("HmacSHA256", secretKey).hmacHex(dataCheckString)

        return (initDataHash == userDataFromTelegram.hash).also {
            println("Validation successful for ${userDataFromTelegram.username}")
        }
    }
}
