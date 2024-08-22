package com.example.tddpracticebymadvirus.chap07.autodebit

import com.example.tddpracticebymadvirus.chap07.autodebit.domain.AutoDebitRegister
import com.example.tddpracticebymadvirus.chap07.autodebit.domain.dto.AutoDebitReq
import com.example.tddpracticebymadvirus.chap07.autodebit.domain.CardValidity
import com.example.tddpracticebymadvirus.chap07.autodebit.domain.StubCardNumberValidator
import com.example.tddpracticebymadvirus.chap07.autodebit.persistence.StubAutoDebitInfoRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AutoDebitRegister_Stub_Test {

    private lateinit var register: AutoDebitRegister
    private lateinit var stubValidator: StubCardNumberValidator
    private lateinit var stubRepository: StubAutoDebitInfoRepository

    @BeforeEach
    fun setUp() {
        stubValidator = StubCardNumberValidator(null, null)
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

    @Test
    fun theftCard() {
        stubValidator.theftNo = "1234567890123456"

        val req = AutoDebitReq("user1", "1234567890123456")
        val result = this.register.register(req)

        assertEquals(CardValidity.THEFT, result.validity)
    }
}