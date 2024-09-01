package com.example.tddpracticebymadvirus.chap09

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.RequestEntity
import java.net.URI
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserApiE2ETest(
    // TestRestTemplate: 스프링 부트가 테스트 목적으로 제공하는 내장 서버와 연결하는 RestTemplate
    @Autowired private val restTemplate: TestRestTemplate
) {
    @Test
    fun weakPWResponse() {
        val reqBody = "{\"id\": \"id\", \"pw\": \"123\", \"email\": \"a@a.com\"}"
        val request = RequestEntity.post(URI.create("/users"))
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .body(reqBody)

        val response = restTemplate.exchange(
            request,
            String::class.java
        )

        assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
        response.body?.let { body -> assertTrue(body.contains("WeakPasswordException")) }
    }
}