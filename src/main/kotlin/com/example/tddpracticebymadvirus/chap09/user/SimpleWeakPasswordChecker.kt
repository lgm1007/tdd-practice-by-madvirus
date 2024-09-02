package com.example.tddpracticebymadvirus.chap09.user

import org.springframework.stereotype.Component

@Component
class SimpleWeakPasswordChecker : WeakPasswordChecker {
    private val passwordMinLength = 5

    override fun checkPasswordWeak(pw: String): Boolean {
        return pw.length < passwordMinLength;
    }
}