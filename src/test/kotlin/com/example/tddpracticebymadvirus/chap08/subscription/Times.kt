package com.example.tddpracticebymadvirus.chap08.subscription

import java.time.LocalDate

open class Times {
    open fun today(): LocalDate {
        return LocalDate.now()
    }
}