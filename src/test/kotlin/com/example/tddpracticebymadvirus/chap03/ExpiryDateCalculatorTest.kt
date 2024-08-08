package com.example.tddpracticebymadvirus.chap03

import com.example.tddpracticebymadvirus.chap03.domain.ExpiryDateCalculator
import com.example.tddpracticebymadvirus.chap03.dto.PayData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDate

class ExpiryDateCalculatorTest {
    /*
    1. 서비스를 사용하려면 매달 1만 원을 선불로 납부한다. 납부일 기준으로 한 달 뒤가 서비스 만료일이 된다.
    2. 2개월 이상 요금을 납부할 수 있다.
    3. 10만 원을 납부하면 서비스를 1년 제공한다.
     */

    @Test
    @DisplayName("만원 납부하면 한달 뒤가 만료일이 되는 것 확인하기")
    fun expirationDateOneMonthLater() {
        val billingDate1: LocalDate = LocalDate.of(2024, 8, 7)
        val billingDate2: LocalDate = LocalDate.of(2020, 12, 20)
        val billingDate3: LocalDate = LocalDate.of(2024, 1, 31)
        val billingDate4: LocalDate = LocalDate.of(2020, 5, 31)
        val payAmount: Int = 10000

        val expiryDate1: LocalDate = LocalDate.of(2024, 9, 7)
        val expiryDate2: LocalDate = LocalDate.of(2021, 1, 20)
        val expiryDate3: LocalDate = LocalDate.of(2024, 2, 29)
        val expiryDate4: LocalDate = LocalDate.of(2020, 6, 30)

        assertAll(
            { assertExpiryDate(PayData(billingDate1, billingDate1, payAmount), expiryDate1) },
            { assertExpiryDate(PayData(billingDate2, billingDate2, payAmount), expiryDate2) },
            { assertExpiryDate(PayData(billingDate3, billingDate3, payAmount), expiryDate3) },
            { assertExpiryDate(PayData(billingDate4, billingDate4, payAmount), expiryDate4) },
        )
    }

    private fun assertExpiryDate(payData: PayData, expiryDate: LocalDate) {
        val calculator = ExpiryDateCalculator()
        val actual = calculator.calculateExpiryDate(payData)
        assertThat(actual).isEqualTo(expiryDate)
    }
}