package com.example.tddpracticebymadvirus.chap08.subscription

import java.time.LocalDate

class Subscription(
    val userId: String,
    val productId: String,
    val expiryDate: LocalDate,
    val grade: Grade,
) {
    fun isFinished(now: LocalDate): Boolean {
        return now.isAfter(expiryDate)
    }
}