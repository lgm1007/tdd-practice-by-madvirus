package com.example.tddpracticebymadvirus.chap07.user

import com.example.tddpracticebymadvirus.chap07.user.domain.SpyEmailNotifier
import com.example.tddpracticebymadvirus.chap07.user.domain.StubWeakPasswordChecker
import com.example.tddpracticebymadvirus.chap07.user.domain.UserRegister
import com.example.tddpracticebymadvirus.chap07.user.exception.DupIdException
import com.example.tddpracticebymadvirus.chap07.user.exception.WeakPasswordException
import com.example.tddpracticebymadvirus.chap07.user.persistence.MemoryUserRepository
import com.example.tddpracticebymadvirus.chap07.user.persistence.entity.User
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class UserRegisterTest {
    private lateinit var userRegister: UserRegister
    private val stubPasswordChecker = StubWeakPasswordChecker(false)
    private val fakeRepository = MemoryUserRepository()
    private val spyEmailNotifier = SpyEmailNotifier(false, null)

    @BeforeEach
    fun setUp() {
        userRegister = UserRegister(stubPasswordChecker,
            fakeRepository,
            spyEmailNotifier)
    }

    @Test
    @DisplayName("약한 암호면 가입 실패")
    fun weakPassword() {
        stubPasswordChecker.weak = true

        assertThrows<WeakPasswordException> {
            userRegister.register("id", "pw", "email")
        }
    }

    @Test
    @DisplayName("이미 같은 아이디가 존재하면 가입 실패")
    fun dupIdExist() {
        // 이미 같은 아이디 존재하는 상황 만들기
        fakeRepository.save(User("id", "pw", "email@email.com"))

        assertThrows<DupIdException> {
            userRegister.register("id", "pw2", "email")
        }
    }

    @Test
    @DisplayName("같은 아이디가 없으면 가입 성공")
    fun noDupIdRegisterSuccess() {
        userRegister.register("id", "pw", "email")

        val savedUser = fakeRepository.findById("id")
        assertEquals("id", savedUser?.id)
        assertEquals("email", savedUser?.email)
    }

    @Test
    @DisplayName("가입하면 메일 전송")
    fun whenRegisterThenSendMail() {
        userRegister.register("id", "pw", "email@email.com")

        assertTrue(spyEmailNotifier.called)
        assertEquals("email@email.com", spyEmailNotifier.email)
    }
}