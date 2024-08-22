package com.example.tddpracticebymadvirus.chap07.user.domain

import com.example.tddpracticebymadvirus.chap07.user.exception.WeakPasswordException

class UserRegister(
    private val passwordChecker: WeakPasswordChecker
) {
    fun register(id: String, pw: String, email: String) {
        if (passwordChecker.checkPasswordWeak(pw)) {
            throw WeakPasswordException()
        }
    }
}