package com.example.tddpracticebymadvirus.chap08.auth

interface CustomerRepository {
    fun findOne(id: String): Customer
}