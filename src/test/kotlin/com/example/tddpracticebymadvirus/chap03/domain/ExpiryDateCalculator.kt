package com.example.tddpracticebymadvirus.chap03.domain

import java.time.LocalDate

class ExpiryDateCalculator {
    fun calculateExpiryDate(billingDate: LocalDate, payAmount: Int): LocalDate {
        return billingDate.plusMonths(1)
    }
}