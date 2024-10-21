package ru.posidata.backend

import ru.posidata.backend.controller.UserController

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import ru.posidata.backend.service.TelegramAuthService
import ru.posidata.backend.service.UserService
import kotlin.test.Test

@WebMvcTest(controllers = [UserController::class], excludeAutoConfiguration = [SecurityAutoConfiguration::class])
class MyControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var userService: UserService

    @MockBean
    private lateinit var telegramAuthService: TelegramAuthService

    @Test
    fun `test user controller`() {
        mockMvc.perform(
            get("/api/get")
                .param("authDate", "1630454400000")
                .param("firstName", "Andrey")
                .param("lastName", "Kuleshov")
                .param("hash", "abc123hash")
                .param("id", "12345")
                .param("photoUrl", "https://example.com/photo.jpg")
                .param("username", "akuleshov7")
        )
            .andExpect(status().isForbidden)
    }
}
