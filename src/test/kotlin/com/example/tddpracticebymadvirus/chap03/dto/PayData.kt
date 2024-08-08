package com.example.tddpracticebymadvirus.chap03.dto

import java.time.LocalDate

class PayData (
    val firstBillingDate: LocalDate,
    val billingDate: LocalDate,
    val payAmount: Int
) {
}