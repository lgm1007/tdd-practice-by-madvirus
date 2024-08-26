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
import org.mockito.kotlin.argumentCaptor
import kotlin.test.assertEquals

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

    @Test
    @DisplayName("회원 가입 암호 검사 수행")
    fun checkPassword() {
        userRegister.register("id", "pw", "email")

        BDDMockito.then(mockPasswordChecker)
            .should()
            // String 타입 인자로 checkPasswordWeak() 메서드가 호출이 되었는지 확인
            .checkPasswordWeak(BDDMockito.anyString())

    }

    @Test
    @DisplayName("가입하면 메일을 전송")
    fun whenRegisterThenSendMail() {
        userRegister.register("id", "pw", "email@email.com")

        // argumentCaptor: 모의 객체 메서드를 호출할 때 전달한 객체를 담는 기능
        // kotlin에서 argumentCaptor 사용 시 mockito-kotlin 라이브러리 사용
        val captor = argumentCaptor<String>()
        BDDMockito.then(mockEmailNotifier)
            .should()
            // captor.capture(): 메서드 호출 시 전달한 인자를 argumentCaptor에 담기
            .sendRegisterEmail(captor.capture())

        val realEmail = captor.firstValue
        assertEquals("email@email.com", realEmail)
    }
}