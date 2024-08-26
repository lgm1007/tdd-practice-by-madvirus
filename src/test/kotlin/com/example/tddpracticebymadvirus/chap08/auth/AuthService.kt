package com.example.tddpracticebymadvirus.chap08.auth

class AuthService {
    private val authKey = "somekey"

    fun authenticate(id: String, pw: String): Int {
        // AuthUtil: 테스트 어려운 외부 라이브러리를 사용하는 메서드
        val authorized = AuthUtil.authorize(authKey)
        return if (authorized) {
            AuthUtil.authenticate(id, pw)
        } else {
            -1
        }
    }
}