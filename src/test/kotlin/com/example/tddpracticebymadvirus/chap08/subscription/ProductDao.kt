package com.example.tddpracticebymadvirus.chap08.subscription

interface ProductDao {
    fun selectById(productId: String): Product
}