package ru.posidata.backend.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.posidata.backend.service.TelegramAuthService


@RestController
@RequestMapping("api")
class TelegramAuthController(private val telegramAuthService: TelegramAuthService) {
    //     <!DOCTYPE html>
    //<html lang="en">
    //<head>
    //    <meta charset="UTF-8">
    //    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    //    <title>Telegram Login</title>
    //    <style>
    //        body {
    //            font-family: Arial, sans-serif;
    //            margin: 0;
    //            padding: 0;
    //            display: flex;
    //            justify-content: center;
    //            align-items: center;
    //            height: 100vh;
    //            background-color: #f4f4f4;
    //        }
    //        .container {
    //            text-align: center;
    //        }
    //        h1 {
    //            margin-bottom: 24px;
    //        }
    //    </style>
    //</head>
    //<body>
    //<div class="container">
    //    <h1>Login with Telegram</h1>
    //    <p>Click the button below to log in using your Telegram account:</p>
    //  <script async src="https://telegram.org/js/telegram-widget.js?22" data-telegram-login="PosiDataBot" data-size="large" data-auth-url="https://posidata.ru/api/test" data-request-access="write"></script>
    //</div>
    //</body>
    //</html>
    // https://posidata.ru/api/test?id=221298772&first_name=Андрей&last_name=Кулешов&username=akuleshov7&photo_url=https%3A%2F%2Ft.me%2Fi%2Fuserpic%2F320%2Fd3fKyG306aXHDBCxZXfWTpGlii6fZqZMo1tBmMPEl_E.jpg&auth_date=1725656645&hash=742dda3a019e57821e1fb7acf9918aeb6f0d734cc5c8612913b6696aeab2c745
    @GetMapping("/test")
    fun test(
        @RequestParam id: String,
        @RequestParam first_name: String,
        @RequestParam last_name: String,
        @RequestParam photo_url: String,
        @RequestParam username: String,
        @RequestParam auth_date: String,
        @RequestParam hash: String
    ): String {
        println(username)
        return username
    }

    @GetMapping("/get")
    fun get(): String {
        println("Hi!")
        return "Hi"
    }


    @PostMapping("auth/telegram")
    fun telegramAuth(@RequestBody request: Map<String, Any>): ResponseEntity<Any> {
        return try {
            val authResult = telegramAuthService.authenticate(request)
            if (authResult.isAuthenticated) {
                ResponseEntity.ok(authResult.token)
            } else {
                ResponseEntity.status(HttpStatus.FORBIDDEN).body("Login info hash mismatch")
            }
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.FORBIDDEN).body("Server error while authenticating")
        }
    }
}
