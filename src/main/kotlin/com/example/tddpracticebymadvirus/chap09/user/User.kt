package com.example.tddpracticebymadvirus.chap09.user

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class User(
    @Id val id: String,
    private val password: String,
    val email: String,
) {
}