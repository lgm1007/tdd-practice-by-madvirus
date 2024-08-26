package com.example.tddpracticebymadvirus.chap08.auth

class LoginResult {
    companion object {
        fun badAuthKey(): LoginResult? {
            return null
        }

        fun authenticated(c: Customer): LoginResult? {
            return null
        }

        fun fail(resp: Int): LoginResult? {
            return null
        }
    }
}