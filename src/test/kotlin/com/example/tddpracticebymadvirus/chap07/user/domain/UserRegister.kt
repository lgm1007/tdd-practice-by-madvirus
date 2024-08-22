package com.example.tddpracticebymadvirus.chap07.user.domain

import com.example.tddpracticebymadvirus.chap07.user.exception.DupIdException
import com.example.tddpracticebymadvirus.chap07.user.exception.WeakPasswordException
import com.example.tddpracticebymadvirus.chap07.user.persistence.UserRepository

class UserRegister(
    private val passwordChecker: WeakPasswordChecker,
    private val userRepository: UserRepository,
) {
    fun register(id: String, pw: String, email: String) {
        if (passwordChecker.checkPasswordWeak(pw)) {
            throw WeakPasswordException()
        }
        val user = userRepository.findById(id)
        if (user != null) {
            throw DupIdException()
        }
    }
}