package com.example.tddpracticebymadvirus.chap07.user.domain

class SpyEmailNotifier(
    val called: Boolean,
    val email: String?,
) : EmailNotifier {
}