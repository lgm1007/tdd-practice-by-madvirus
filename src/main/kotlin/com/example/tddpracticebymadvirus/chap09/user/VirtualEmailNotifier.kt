package com.example.tddpracticebymadvirus.chap09.user

import org.springframework.stereotype.Component

@Component
class VirtualEmailNotifier : EmailNotifier {
    override fun sendRegisterEmail(email: String) {
        println("메일 발송: $email")
    }
}