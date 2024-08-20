package com.example.tddpracticebymadvirus.chap07

import com.example.tddpracticebymadvirus.chap07.domain.AutoDebitRegister
import com.example.tddpracticebymadvirus.chap07.domain.AutoDebitReq
import com.example.tddpracticebymadvirus.chap07.domain.CardValidity
import com.example.tddpracticebymadvirus.chap07.domain.StubCardNumberValidator
import com.example.tddpracticebymadvirus.chap07.persistence.StubAutoDebitInfoRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AutoDebitRegister_Stub_Test {

    private lateinit var register: AutoDebitRegister
    private lateinit var stubValidator: StubCardNumberValidator
    private lateinit var stubRepository: StubAutoDebitInfoRepository

    @BeforeEach
    fun setUp() {
        stubValidator = StubCardNumberValidator(null)
        stubRepository = StubAutoDebitInfoRepository()
        register = AutoDebitRegister(stubValidator, stubRepository)
    }

    @Test
    fun invalidCard() {
        stubValidator.invalidNo = "111122223333"

        val req = AutoDebitReq("user1", "111122223333")
        val result = this.register.register(req)

        assertEquals(CardValidity.INVALID, result.validity)
    }
}