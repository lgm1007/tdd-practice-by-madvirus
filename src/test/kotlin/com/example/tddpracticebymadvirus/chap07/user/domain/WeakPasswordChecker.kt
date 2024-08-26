package com.example.tddpracticebymadvirus.chap07.user.domain

interface WeakPasswordChecker {
    fun checkPasswordWeak(pw: String): Boolean
}