package com.example.tddpracticebymadvirus.chap07.user.domain

interface EmailNotifier {
    fun sendRegisterEmail(email: String)
}