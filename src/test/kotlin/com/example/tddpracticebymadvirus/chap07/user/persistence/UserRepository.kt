package com.example.tddpracticebymadvirus.chap07.user.persistence

import com.example.tddpracticebymadvirus.chap07.user.persistence.entity.User

interface UserRepository {
    fun save(user: User)

    fun findById(id: String): User?
}