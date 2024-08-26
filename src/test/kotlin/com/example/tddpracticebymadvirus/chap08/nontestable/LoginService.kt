package com.example.tddpracticebymadvirus.chap08.nontestable

import com.example.tddpracticebymadvirus.chap08.auth.AuthUtil
import com.example.tddpracticebymadvirus.chap08.auth.CustomerRepository
import com.example.tddpracticebymadvirus.chap08.auth.LoginResult

class LoginService(
    private val customerRepository: CustomerRepository
) {
    private val authKey = "somekey"

    fun login(id: String, pw: String): LoginResult? {
        var resp = 0
        val authorized = AuthUtil.authorize(authKey)

        resp = if (authorized) {
            AuthUtil.authenticate(id, pw)
        } else {
            -1
        }

        return when (resp) {
            -1 -> {
                LoginResult.badAuthKey()
            }
            1 -> {
                val customer = customerRepository.findOne(id)
                LoginResult.authenticated(customer)
            }
            else -> {
                LoginResult.fail(resp)
            }
        }

    }
}