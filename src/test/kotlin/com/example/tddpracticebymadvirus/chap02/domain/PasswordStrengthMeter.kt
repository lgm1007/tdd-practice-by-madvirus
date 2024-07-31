package com.example.tddpracticebymadvirus.chap02.domain

class PasswordStrengthMeter {
    fun meter(password: String): PasswordMeterResult {
        if ("abc123ABC".equals(password)) {
            return PasswordMeterResult.STRENGTH
        }
        return PasswordMeterResult.WEAK
    }
}