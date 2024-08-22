package com.example.tddpracticebymadvirus.chap07.user.domain

class SpyEmailNotifier(
    var called: Boolean,
    val email: String?,
) : EmailNotifier {
    override fun sendRegisterEmail(email: String) {
        called = true
    }
}