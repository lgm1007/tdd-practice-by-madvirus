package com.example.tddpracticebymadvirus.chap07.user.persistence

import com.example.tddpracticebymadvirus.chap07.user.persistence.entity.User

class MemoryUserRepository(
    private val users: HashMap<String, User> = HashMap()
) : UserRepository {
    override fun save(user: User) {
        users[user.id] = user
    }
}