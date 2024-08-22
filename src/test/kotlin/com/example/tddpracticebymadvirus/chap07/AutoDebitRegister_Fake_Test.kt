package com.example.tddpracticebymadvirus.chap07

import com.example.tddpracticebymadvirus.chap07.domain.AutoDebitRegister
import com.example.tddpracticebymadvirus.chap07.domain.AutoDebitReq
import com.example.tddpracticebymadvirus.chap07.domain.StubCardNumberValidator
import com.example.tddpracticebymadvirus.chap07.persistence.MemoryAutoDebitInfoRepository
import com.example.tddpracticebymadvirus.chap07.persistence.entity.AutoDebitInfo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import kotlin.test.assertEquals

class AutoDebitRegister_Fake_Test {
    private lateinit var register: AutoDebitRegister
    private lateinit var cardNumberValidator: StubCardNumberValidator
    private lateinit var repository: MemoryAutoDebitInfoRepository

    @BeforeEach
    fun setUp() {
        cardNumberValidator = StubCardNumberValidator(null, null)
        repository = MemoryAutoDebitInfoRepository()
        register = AutoDebitRegister(cardNumberValidator, repository)
    }

    @Test
    fun alreadyRegisterdInfoUpdated() {
        val userId = "user1"
        val cardNumber = "123456789012"

        repository.save(
            AutoDebitInfo(userId, "111222333444", LocalDateTime.now())
        )

        val req = AutoDebitReq(userId, cardNumber)
        val result = this.register.register(req)

        val savedInfo = repository.findOne(userId)
        assertEquals(cardNumber, savedInfo?.getCardNumber())
    }

    @Test
    fun notYetRegisteredNewInfoRegistered() {
        val userId = "user1"
        val cardNumber = "1234123412341234"
        val req = AutoDebitReq(userId, cardNumber)
        val result = this.register.register(req)

        val savedInfo = repository.findOne(userId)
        assertEquals(cardNumber, savedInfo?.getCardNumber())
    }
}