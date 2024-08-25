package com.example.tddpracticebymadvirus.chap07.user

import com.example.tddpracticebymadvirus.chap07.user.domain.EmailNotifier
import com.example.tddpracticebymadvirus.chap07.user.domain.UserRegister
import com.example.tddpracticebymadvirus.chap07.user.domain.WeakPasswordChecker
import com.example.tddpracticebymadvirus.chap07.user.exception.WeakPasswordException
import com.example.tddpracticebymadvirus.chap07.user.persistence.MemoryUserRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.BDDMockito
import org.mockito.Mockito

class UserRegisterMockTest {
    private lateinit var userRegister: UserRegister
    private val mockPasswordChecker = Mockito.mock(WeakPasswordChecker::class.java)
    private val fakeRepository = MemoryUserRepository()
    private val mockEmailNotifier = Mockito.mock(EmailNotifier::class.java)

    @BeforeEach
    fun setUp() {
        userRegister = UserRegister(mockPasswordChecker, fakeRepository, mockEmailNotifier)
    }

    @Test
    @DisplayName("약한 암호면 가입 실패")
    fun weakPassword() {
        // "pw " 인자를 사용해서 모의 객체의 checkPasswordWeak() 메서드를 호출하면
        BDDMockito.given(mockPasswordChecker.checkPasswordWeak("pw"))
            // 결과를 true로 반환하라
            .willReturn(true);

        assertThrows<WeakPasswordException> {
            userRegister.register("id", "pw", "email")
        }
    }
}