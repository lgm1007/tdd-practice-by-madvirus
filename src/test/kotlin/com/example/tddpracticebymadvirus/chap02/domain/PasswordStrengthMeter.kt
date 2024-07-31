package com.example.tddpracticebymadvirus.chap02.domain

const val PASSWORD_CONDITION_LENGTH = 8

class PasswordStrengthMeter {

    fun meter(password: String): PasswordMeterResult {
        val condition8Length = condition8Length(password)
        val conditionContainsNumber = conditionContainsNumber(password)
        val conditionContainsCapitalLetter = conditionContainsCapitalLetter(password)

        if (condition8Length &&
            conditionContainsNumber &&
            conditionContainsCapitalLetter
        ) {
            return PasswordMeterResult.STRENGTH
        }

        if ((condition8Length && conditionContainsNumber) ||
            (condition8Length && conditionContainsCapitalLetter) ||
            (conditionContainsNumber && conditionContainsCapitalLetter)) {
            return PasswordMeterResult.NORMAL
        }

        return PasswordMeterResult.WEAK
    }

    private fun condition8Length(password: String): Boolean {
        return password.length >= PASSWORD_CONDITION_LENGTH
    }

    private fun conditionContainsNumber(password: String): Boolean {
        for (c in password.toCharArray()) {
            if (c in '0'..'9') {
                return true
            }
            continue
        }
        return false
    }

    private fun conditionContainsCapitalLetter(password: String): Boolean {
        for (c in password.toCharArray()) {
            if (c in 'A'..'Z') {
                return true
            }
            continue
        }
        return false
    }
}