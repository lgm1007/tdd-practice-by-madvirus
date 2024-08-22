package com.example.tddpracticebymadvirus.chap07.user.domain

class StubWeakPasswordChecker(var weak: Boolean) : WeakPasswordChecker {
    override fun checkPasswordWeak(pw: String): Boolean {
        return weak
    }
}