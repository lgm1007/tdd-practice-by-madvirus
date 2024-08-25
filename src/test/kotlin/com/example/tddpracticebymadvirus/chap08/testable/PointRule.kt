package com.example.tddpracticebymadvirus.chap08.testable

import com.example.tddpracticebymadvirus.chap08.subscription.Grade
import com.example.tddpracticebymadvirus.chap08.subscription.Product
import com.example.tddpracticebymadvirus.chap08.subscription.Subscription
import java.time.LocalDate

class PointRule {
    fun calculate(s: Subscription, p: Product, now: LocalDate): Int {
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