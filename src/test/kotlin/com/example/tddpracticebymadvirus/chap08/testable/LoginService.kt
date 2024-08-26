package com.example.tddpracticebymadvirus.chap08.testable

import com.example.tddpracticebymadvirus.chap08.auth.AuthService
import com.example.tddpracticebymadvirus.chap08.auth.CustomerRepository
import com.example.tddpracticebymadvirus.chap08.auth.LoginResult

class LoginService(
    private val customerRepository: CustomerRepository,
) {
    private var authService = AuthService()

    fun setAuthService(authService: AuthService) {
        this.authService = authService
    }

    fun login(id: String, pw: String): LoginResult? {
        // 대역으로 사용 가능하게 분리
        val resp = authService.authenticate(id, pw)
        if (resp == -1) return LoginResult.badAuthKey()

        return if (resp == 1) {
            val customer = customerRepository.findOne(id)
            LoginResult.authenticated(customer)
        } else {
            LoginResult.fail(resp)
        }
    }
}