package ru.posidata.backend.service

import org.apache.commons.codec.digest.HmacUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service


@Service
class TelegramAuthService(
    @Value("\${telegram.bot.token}") private val telegramToken: String,
) {
    fun isValidHash(hash: String): Boolean {
        val secreteKey = "1289756607:AAFYuBeHguJKYhDVzwSfYFLX4tF5YbkSU7M"
        return true
    }

}
/*

fun main() {
    println(isValidHash())
}

// Bot token
const val BOT_TOKEN = "7518590686:AAFCT7m70_ANfOZfIACr2C7bOy0igdLNOpg"

// Function to validate hash
fun isValidHash(): Boolean {
    val hash = "742dda3a019e57821e1fb7acf9918aeb6f0d734cc5c8612913b6696aeab2c745"

    val parsedData: Map<String, String> = mapOf(
        "id" to "221298772",
        "first_name" to "Андрей",
        "last_name" to "Кулешов",
        "username" to "akuleshov7",
        "photo_url" to "https%3A%2F%2Ft.me%2Fi%2Fuserpic%2F320%2Fd3fKyG306aXHDBCxZXfWTpGlii6fZqZMo1tBmMPEl_E.jpg",
        "auth_date" to "1725656645",
        "hash" to "742dda3a019e57821e1fb7acf9918aeb6f0d734cc5c8612913b6696aeab2c745"
    )

    // Remove 'hash' value & sort alphabetically
    val dataKeys = parsedData.keys.filter { it != "hash" }.sorted()

    // Create line format key=<value>
    val items = dataKeys.map { key -> "$key=${parsedData[key]}" }

    // Create check string with '\n' as separator
    val dataCheckString = items.joinToString("\n")

    val secretKey: ByteArray = HmacUtils("HmacSHA256", "WebAppData").hmac(BOT_TOKEN)
    val initDataHash: String = HmacUtils("HmacSHA256", secretKey).hmacHex(dataCheckString)

    println(initDataHash)
    println(hash)
    // Return whether the generated hash matches the provided hash
    return initDataHash == hash
}
*/
