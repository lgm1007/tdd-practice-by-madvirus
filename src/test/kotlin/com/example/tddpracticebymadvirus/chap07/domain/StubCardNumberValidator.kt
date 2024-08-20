package com.example.tddpracticebymadvirus.chap07.domain

class StubCardNumberValidator(var invalidNo: String?) : CardNumberValidator() {
    override fun validate(cardNumber: String): CardValidity {
        if (invalidNo != null && invalidNo.equals(cardNumber)) {
            return CardValidity.INVALID
        }
        return CardValidity.VALID
    }
}