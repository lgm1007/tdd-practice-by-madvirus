package com.example.tddpracticebymadvirus.chap08.testable

import com.example.tddpracticebymadvirus.chap08.subscription.Times
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.mockito.Mockito
import java.time.LocalDate
import kotlin.test.assertEquals

class DailyBatchLoaderTest {
    private val mockTimes = Mockito.mock(Times::class.java)
    private val loader = DailyBatchLoader()

    @BeforeEach
    fun setUp() {
        loader.setBasePath("src/test/resources")
        loader.setTimes(mockTimes)
    }

    @Test
    fun loadCount() {
        BDDMockito.given(mockTimes.today()).willReturn(LocalDate.of(2024, 8, 25))

        val ret = loader.load()

        assertEquals(3, ret)
    }
}