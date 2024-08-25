package com.example.tddpracticebymadvirus.chap08.nontestable

import com.example.tddpracticebymadvirus.chap08.subscription.Grade
import com.example.tddpracticebymadvirus.chap08.subscription.ProductDao
import com.example.tddpracticebymadvirus.chap08.subscription.SubscriptionDao
import com.example.tddpracticebymadvirus.chap08.subscription.User
import com.example.tddpracticebymadvirus.chap08.subscription.exception.NoSubscriptionException
import java.time.LocalDate

class UserPointCalculator(
    // 테스트하기 위해선 SubscriptionDao, ProductDao에 대한 대역 구성 필요
    private val subscriptionDao: SubscriptionDao,
    private val productDao: ProductDao,
) {
    fun calculatePoint(u: User): Int {
        val s = subscriptionDao.selectByUser(u.id) ?: throw NoSubscriptionException()
        val p = productDao.selectById(s.productId)
        val now = LocalDate.now()

        var point = 0
        point += if (s.isFinished(now)) {
            p.defaultPoint
        } else {
            p.defaultPoint + 10
        }

        if (s.grade == Grade.GOLD) {
            point += 100
        }

        return point
    }
}