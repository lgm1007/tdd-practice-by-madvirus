package com.example.tddpracticebymadvirus.chap09.autodebit

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpRequest.BodyPublishers
import java.net.http.HttpResponse.BodyHandlers
import java.net.http.HttpTimeoutException
import java.time.Duration

class CardNumberValidator(
    private var server: String,
) {
    fun validate(cardNumber: String): CardValidity {
        val httpClient = HttpClient.newHttpClient()
        val httpRequest = HttpRequest.newBuilder()
            .uri(URI.create("$server/card"))
            .header("Content-Type", "text/plain")
            .POST(BodyPublishers.ofString(cardNumber))
            .timeout(Duration.ofSeconds(3))
            .build()

        try {
            val httpResponse = httpClient.send(httpRequest, BodyHandlers.ofString())
            return when (httpResponse.body()) {
                "ok" -> CardValidity.VALID
                "bad" -> CardValidity.INVALID
                "expired" -> CardValidity.EXPIRED
                "theft" -> CardValidity.THEFT
                else -> CardValidity.UNKNOWN
            }
        } catch (e: HttpTimeoutException) {
            return CardValidity.TIMEOUT
        } catch (e: Exception) {
            return CardValidity.ERROR
        }
    }
}