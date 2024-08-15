package com.example.tddpracticebymadvirus.chap07

import com.example.tddpracticebymadvirus.chap07.domain.AutoDebitRegister
import com.example.tddpracticebymadvirus.chap07.domain.CardNumberValidator
import org.junit.jupiter.api.BeforeEach

class AutoDebitRegisterTest(
    private var register: AutoDebitRegister
) {
    @BeforeEach
    fun setUp() {
        val validator = CardNumberValidator()
        val repository = JpaAutoDebitInfoRepository()
        register = AutoDebitRegister(validator, repository)
    }
}