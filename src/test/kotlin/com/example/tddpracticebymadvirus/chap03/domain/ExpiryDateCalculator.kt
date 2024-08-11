package com.example.tddpracticebymadvirus.chap03.domain

import com.example.tddpracticebymadvirus.chap03.dto.PayData
import java.time.LocalDate
import java.time.YearMonth

class ExpiryDateCalculator {
    fun calculateExpiryDate(payData: PayData): LocalDate {
        val addMonth: Int = payData.payAmount / 10000;
        val plusMonthsBillingDate = payData.billingDate.plusMonths(addMonth.toLong())

        if (payData.firstBillingDate != null) {
            if (payData.firstBillingDate == payData.billingDate) {
                return plusMonthsBillingDate
            }

            // dayOfMonth: 해당 월의 일을 반환 (1~31)
            if (payData.firstBillingDate.dayOfMonth != plusMonthsBillingDate.dayOfMonth) {
                // 예상 만료일이 포함된 달의 마지막 날 < 첫 납부일의 일자
                if (YearMonth.from(plusMonthsBillingDate).lengthOfMonth() < payData.firstBillingDate.dayOfMonth) {
                    // 예상 만료일의 마지막 날로 일자를 조정
                    return plusMonthsBillingDate.withDayOfMonth(
                        YearMonth.from(plusMonthsBillingDate).lengthOfMonth()
                    )
                }
                // withDayOfMonth(int day): day 값을 일로 받은 날짜로 변환
                return plusMonthsBillingDate.withDayOfMonth(payData.firstBillingDate.dayOfMonth)
            }
        }

        return plusMonthsBillingDate
    }
}