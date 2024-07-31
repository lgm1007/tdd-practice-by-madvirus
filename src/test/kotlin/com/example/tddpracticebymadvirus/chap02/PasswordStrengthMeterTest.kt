package com.example.tddpracticebymadvirus.chap02

import com.example.tddpracticebymadvirus.chap02.domain.PasswordMeterResult
import com.example.tddpracticebymadvirus.chap02.domain.PasswordStrengthMeter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PasswordStrengthMeterTest {
    /*
    1. 길이가 8 이상
    2. 0부터 9 사이의 숫자를 포함
    3. 대문자 포함
     */

    @Test
    @DisplayName("암호가 모든 조건 충족하는지 확인하기")
    fun meetsAllConditionStrength() {
        val meter: PasswordStrengthMeter = PasswordStrengthMeter()
        val result: PasswordMeterResult = meter.meter("abc123ABC")
        assertThat(result).isEqualTo(PasswordMeterResult.STRENGTH)
    }
}