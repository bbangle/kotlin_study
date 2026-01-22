package com.example.mvc.controller.exception

import com.example.mvc.model.http.UserRequest
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.util.LinkedMultiValueMap
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@WebMvcTest
@AutoConfigureMockMvc
class ExceptionApiControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @Test
    fun helloTest() {
        mvc.perform(MockMvcRequestBuilders.get("/api/exception/hello"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("hello"))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun getTest() {
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "test")
        queryParams.add("age", "20")

        mvc.perform(MockMvcRequestBuilders.get("/api/exception").queryParams(queryParams))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("test - 20"))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun getFailtest() {
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "test")
        queryParams.add("age", "9")

        mvc.perform(MockMvcRequestBuilders.get("/api/exception").queryParams(queryParams))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(jsonPath("\$.result_code").value("FAIL"))
            .andExpect(jsonPath("\$.errors[0].field").value("age"))
            .andExpect(jsonPath("\$.errors[0].value").value("9"))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun postTest() {

        val userRequest = UserRequest().apply {
            this.name = "test"
            this.age = 10
            this.phoneNumber = "010-1234-1234"
            this.address = "서울특별시 별별구"
            this.email = "test@example.com"
            this.createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest)
        println(json)

        mvc.perform(
            MockMvcRequestBuilders.post("/api/exception")
                .content(json)
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.name").value("test"))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.age").value(10))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.phoneNumber").value("010-1234-1234"))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.address").value("서울특별시 별별구"))
            .andDo(MockMvcResultHandlers.print())

    }

    @Test
    fun postFailTest() {

        val userRequest = UserRequest().apply {
            this.name = "testtttttttt"
            this.age = 10
            this.phoneNumber = "010-1234-1234"
            this.address = "서울특별시 별별구"
            this.email = "test@example.com"
            this.createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest)
        println(json)

        mvc.perform(
            MockMvcRequestBuilders.post("/api/exception")
                .content(json)
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andDo(MockMvcResultHandlers.print())

    }
}