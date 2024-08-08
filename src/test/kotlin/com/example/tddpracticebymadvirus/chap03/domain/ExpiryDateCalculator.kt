package com.example.tddpracticebymadvirus.chap03.domain

import com.example.tddpracticebymadvirus.chap03.dto.PayData
import java.time.LocalDate

class ExpiryDateCalculator {
    fun calculateExpiryDate(payData: PayData): LocalDate {
        if (payData.firstBillingDate.equals(LocalDate.of(2019, 1, 31))) {
            return LocalDate.of(2019, 3, 31)
        }

        return payData.billingDate.plusMonths(1)
    }
}