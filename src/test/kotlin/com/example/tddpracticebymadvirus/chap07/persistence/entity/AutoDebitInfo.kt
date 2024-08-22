package com.example.tddpracticebymadvirus.chap07.persistence.entity

import java.time.LocalDateTime

class AutoDebitInfo(
    val userId: String,
    private var cardNumber: String,
    private var registrationDate: LocalDateTime,
) {
    fun changeCardNumber(cardNumber: String) {
        this.cardNumber = cardNumber
    }

    fun getCardNumber(): String {
        return this.cardNumber
    }
}