package cn.gaoyuexiang.spring.practice.cors.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpHeaders.ORIGIN
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
internal class HelloControllerTest(@Autowired val mockMvc: MockMvc, @LocalServerPort val port: Int) {

    private val api = "/hello"

    private val username = "cors-tester"

    private val password = "tester"

    @Test
    internal fun shouldGetResponseSuccessWithoutOriginHeader() {
        this.mockMvc.perform(get(api).with(SecurityMockMvcRequestPostProcessors.httpBasic(username, password)))
                .andExpect(status().isOk)
                .andExpect(content().string("Hello, CORS!"))
    }

    @Test
    internal fun shouldGetResponseSuccessWithAllowedOrigin() {
        this.mockMvc.perform(get(api).header(ORIGIN, "http://localhost:8080").with(SecurityMockMvcRequestPostProcessors.httpBasic(username, password)))
                .andExpect(status().isOk)
                .andExpect(content().string("Hello, CORS!"))
    }

    @Test
    internal fun shouldGetForbiddenWithNotAllowedOrigin() {
        this.mockMvc.perform(get("http://localhost:$port$api").header(ORIGIN, "http://localhost"))
                .andExpect(status().isForbidden)
                .andExpect(content().string("Invalid CORS request"))
    }
}