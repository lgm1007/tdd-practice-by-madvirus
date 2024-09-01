package com.example.tddpracticebymadvirus.chap09.autodebit

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CardNumberValidatorTest {
    // HTTP 서버를 흉내내는 WireMockServer
    private lateinit var wireMockServer: WireMockServer

    @BeforeEach
    fun setUp() {
        wireMockServer = WireMockServer(options().port(8090))
        wireMockServer.start()
    }

    @AfterEach
    fun tearDown() {
        wireMockServer.stop()
    }

    @Test
    fun valid() {
        val cardNumber = "1234567890"
        wireMockServer.stubFor(post(urlEqualTo("/card"))    // "/card" URL을 POST 요청
            .withRequestBody(equalTo(cardNumber))   // 요청 body에 cardNumber 삽입
            .willReturn(aResponse()     // 다음과 같이 응답
                .withHeader("Content-Type", "text/plain")   // Content-Type이 text/plain
                .withBody("ok")     // 응답 body가 ok
            )
        )

        val validator = CardNumberValidator("http://localhost:8089")
        val validity = validator.validate(cardNumber)
        assertEquals(CardValidity.VALID, validity)
    }

    @Test
    fun timeout() {
        wireMockServer.stubFor(post(urlEqualTo("/card"))
            .willReturn(aResponse()
                .withFixedDelay(5000)
            )
        )

        val validator = CardNumberValidator("http://localhost:8089")
        val validity = validator.validate("1234567890")
        assertEquals(CardValidity.TIMEOUT, validity)
    }
}