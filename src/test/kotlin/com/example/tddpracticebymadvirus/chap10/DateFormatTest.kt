package com.example.tddpracticebymadvirus.chap10

import org.junit.jupiter.api.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class DateFormatTest {
    @Test
    fun dateFormat() {
        val date = LocalDate.of(1945, 8, 15)
        val dateStr = formatDate(date)
        assertEquals("1945년 8월 15일", dateStr)
    }

    private fun formatDate(date: LocalDate): String {
        return "${date.year}년 ${date.monthValue}월 ${date.dayOfMonth}일"
    }
}