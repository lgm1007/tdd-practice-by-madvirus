package com.example.tddpracticebymadvirus.chap08.subscription

interface SubscriptionDao {
    fun selectByUser(id: String): Subscription?

    fun insert(subscription: Subscription)
}