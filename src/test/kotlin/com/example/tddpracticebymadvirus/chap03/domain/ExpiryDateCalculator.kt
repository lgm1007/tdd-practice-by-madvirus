package com.example.tddpracticebymadvirus.chap03.domain

import com.example.tddpracticebymadvirus.chap03.dto.PayData
import java.time.LocalDate

class ExpiryDateCalculator {
    fun calculateExpiryDate(payData: PayData): LocalDate {
        val plusMonthsBillingDate = payData.billingDate.plusMonths(1)

        if (payData.firstBillingDate == payData.billingDate) {
            return plusMonthsBillingDate
        }

        // dayOfMonth: 해당 월의 일을 반환 (1~31)
        if (payData.firstBillingDate.dayOfMonth != plusMonthsBillingDate.dayOfMonth) {
            // withDayOfMonth(int day): day 값을 일로 받은 날짜로 변환
            return plusMonthsBillingDate.withDayOfMonth(payData.firstBillingDate.dayOfMonth)
        }

        return plusMonthsBillingDate
    }
}