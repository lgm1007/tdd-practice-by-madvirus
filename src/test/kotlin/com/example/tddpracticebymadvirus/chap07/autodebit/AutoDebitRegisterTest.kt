package com.example.tddpracticebymadvirus.chap07.autodebit

import com.example.tddpracticebymadvirus.chap07.autodebit.domain.AutoDebitRegister
import com.example.tddpracticebymadvirus.chap07.autodebit.domain.dto.AutoDebitReq
import com.example.tddpracticebymadvirus.chap07.autodebit.domain.CardNumberValidator
import com.example.tddpracticebymadvirus.chap07.autodebit.domain.CardValidity
import com.example.tddpracticebymadvirus.chap07.autodebit.persistence.JpaAutoDebitInfoRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AutoDebitRegisterTest {

    private lateinit var register: AutoDebitRegister

    @BeforeEach
    fun setUp() {
        val validator = CardNumberValidator()
        val repository = JpaAutoDebitInfoRepository()
        register = AutoDebitRegister(validator, repository)
    }

    @Test
    fun validCard() {
        // 업체에서 받은 테스트용 유효한 카드번호 사용
        val req = AutoDebitReq("user1", "1234123412341234")
        val result = this.register.register(req)
        assertEquals(CardValidity.VALID, result.validity)
    }

    @Test
    fun theftCard() {
        // 업체에서 받은 도난 테스트용 카드번호 사용
        val req = AutoDebitReq("user1", "1234567890123456")
        val result = this.register.register(req)
        assertEquals(CardValidity.THEFT, result.validity)
    }
}