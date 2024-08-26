package com.example.tddpracticebymadvirus.chap07.user.domain

class SpyEmailNotifier(
    var called: Boolean,
    var email: String?,
) : EmailNotifier {
    override fun sendRegisterEmail(email: String) {
        called = true
        this.email = email
    }
}