package com.example.tddpracticebymadvirus.chap03.domain

import com.example.tddpracticebymadvirus.chap03.dto.PayData
import java.time.LocalDate
import java.time.YearMonth

const val ONE_YEAR_PAY_AMOUNT = 100000
const val ONE_MONTH_PAY_AMOUNT = 10000

class ExpiryDateCalculator {
    fun calculateExpiryDate(payData: PayData): LocalDate {
        val addYear: Int = payData.payAmount / ONE_YEAR_PAY_AMOUNT
        val addMonth: Int = (payData.payAmount - (addYear * ONE_YEAR_PAY_AMOUNT)) / ONE_MONTH_PAY_AMOUNT
        val expectedExpiryDate = payData.billingDate.plusYears(addYear.toLong()).plusMonths(addMonth.toLong())

        if (payData.firstBillingDate != null) {
            if (payData.firstBillingDate == payData.billingDate) {
                return expectedExpiryDate
            }

            if (!isSameDayOfMonth(payData.firstBillingDate, expectedExpiryDate)) {
                // dayOfMonth: 해당 날짜의 일자를 반환 (1~31)
                val dayOfFirstBilling: Int = payData.firstBillingDate.dayOfMonth
                val dayLengthOfExpectedExpiryDate: Int = lastDayOfMonth(expectedExpiryDate)

                // 예상 만료일이 포함된 달의 마지막 날 < 첫 납부일의 일자
                if (dayLengthOfExpectedExpiryDate < dayOfFirstBilling) {
                    // 예상 만료일의 마지막 날로 일자를 조정
                    return expectedExpiryDate.withDayOfMonth(
                        dayLengthOfExpectedExpiryDate
                    )
                }
                // withDayOfMonth(int day): day 값을 일로 받은 날짜로 변환
                return expectedExpiryDate.withDayOfMonth(dayOfFirstBilling)
            }
        }

        return expectedExpiryDate
    }

    /**
     * 두 날짜가 같은 일수를 가지고 있는 달인지 확인하기
     */
    private fun isSameDayOfMonth(date1: LocalDate, date2: LocalDate): Boolean {
        return date1.dayOfMonth == date2.dayOfMonth
    }

    /**
     * 달의 마지막 일자를 구하는 메서드
     */
    private fun lastDayOfMonth(date: LocalDate): Int {
        return YearMonth.from(date).lengthOfMonth()
    }
}