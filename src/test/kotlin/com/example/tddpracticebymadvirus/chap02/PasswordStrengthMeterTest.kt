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

    세 조건 모두 충족 시 STRENGTH
    두 조건 충족 시 NORMAL
    한 조건 이하로 충족 시 WEAK
     */

    @Test
    @DisplayName("암호가 모든 조건 충족하는지 확인하기")
    fun meetsAllConditionStrength() {
        val meter: PasswordStrengthMeter = PasswordStrengthMeter()
        val result: PasswordMeterResult = meter.meter("abc123ABC")
        assertThat(result).isEqualTo(PasswordMeterResult.STRENGTH)
    }

    @Test
    @DisplayName("길이가 8 이상인 비밀번호 확인하기")
    fun meets8LengthConditionStrength() {
        val meter: PasswordStrengthMeter = PasswordStrengthMeter()
        val result: PasswordMeterResult = meter.meter("abcdefgh")
        assertThat(result).isEqualTo(PasswordMeterResult.WEAK)
    }

    @Test
    @DisplayName("숫자로만 이루어진 비밀번호 확인하기")
    fun meetNumbericConditionStrength() {
        val meter: PasswordStrengthMeter = PasswordStrengthMeter()
        val result: PasswordMeterResult = meter.meter("123456")
        assertThat(result).isEqualTo(PasswordMeterResult.WEAK)
    }

    @Test
    @DisplayName("대문자로만 이루어진 비밀번호 확인하기")
    fun meetCapitalLetterConditionStrength() {
        val meter: PasswordStrengthMeter = PasswordStrengthMeter()
        val result: PasswordMeterResult = meter.meter("ABCD")
        assertThat(result).isEqualTo(PasswordMeterResult.WEAK)
    }
}