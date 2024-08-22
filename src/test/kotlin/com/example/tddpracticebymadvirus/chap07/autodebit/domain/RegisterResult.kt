package com.example.tddpracticebymadvirus.chap07.autodebit.domain

class RegisterResult(
    val validity: CardValidity
) {
    companion object {
        fun error(validity: CardValidity): RegisterResult {
            return RegisterResult(validity)
        }

        fun success(): RegisterResult {
            return RegisterResult(CardValidity.VALID)
        }
    }
}