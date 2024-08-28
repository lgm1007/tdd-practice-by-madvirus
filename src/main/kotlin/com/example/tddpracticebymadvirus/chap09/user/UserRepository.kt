package com.example.tddpracticebymadvirus.chap09.user

import org.springframework.data.repository.Repository

interface UserRepository : Repository<User, String> {
    fun save(user: User)

    fun findById(id: String): User?
}