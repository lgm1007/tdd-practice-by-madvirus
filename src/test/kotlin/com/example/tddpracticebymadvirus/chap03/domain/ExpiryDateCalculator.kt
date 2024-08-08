package com.example.tddpracticebymadvirus.chap03.domain

import com.example.tddpracticebymadvirus.chap03.dto.PayData
import java.time.LocalDate

class ExpiryDateCalculator {
    fun calculateExpiryDate(payData: PayData): LocalDate {
        return payData.billingDate.plusMonths(1)
    }
}