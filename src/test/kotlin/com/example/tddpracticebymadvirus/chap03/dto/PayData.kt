package com.example.tddpracticebymadvirus.chap03.dto

import java.time.LocalDate

class PayData (
    val billingDate: LocalDate,
    val firstBillingDate: LocalDate,
    val payAmount: Int
) {
}