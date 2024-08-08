package com.example.tddpracticebymadvirus.chap03

import com.example.tddpracticebymadvirus.chap03.domain.ExpiryDateCalculator
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
        val billingDate2: LocalDate = LocalDate.of(2020, 12, 20 )
        val payAmount: Int = 10000

        val calculator: ExpiryDateCalculator = ExpiryDateCalculator()
        val expiryDate1: LocalDate = calculator.calculateExpiryDate(billingDate1, payAmount)
        val expiryDate2: LocalDate = calculator.calculateExpiryDate(billingDate2, payAmount)

        assertAll(
            { assertThat(expiryDate1).isEqualTo(LocalDate.of(2024, 9, 7)) },
            { assertThat(expiryDate2).isEqualTo(LocalDate.of(2021, 1, 20)) }
        )
    }
}