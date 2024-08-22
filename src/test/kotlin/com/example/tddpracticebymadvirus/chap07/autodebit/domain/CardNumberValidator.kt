package com.example.tddpracticebymadvirus.chap07.autodebit.domain

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpRequest.BodyPublishers
import java.net.http.HttpResponse.BodyHandlers

open class CardNumberValidator {
    open fun validate(cardNumber: String): CardValidity {
        val httpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create("https://some-external-pg.com/card"))
            .header("Content-Type", "text/plain")
            .POST(BodyPublishers.ofString(cardNumber))
            .build()

        try {
            val response = httpClient.send(request, BodyHandlers.ofString())
            when(response.body()) {
                "ok" -> return CardValidity.VALID
                "bad" -> return CardValidity.INVALID
                "expired" -> return CardValidity.EXPIRED
                "theft" -> return CardValidity.THEFT
                else -> return CardValidity.UNKNOWN
            }
        } catch (e: Exception) {
            return CardValidity.ERROR
        }
    }
}