package com.example.tddpracticebymadvirus.chap07.domain

class StubCardNumberValidator(
    var invalidNo: String?,
    var theftNo: String?,
) : CardNumberValidator() {
    override fun validate(cardNumber: String): CardValidity {
        if (invalidNo != null && invalidNo.equals(cardNumber)) {
            return CardValidity.INVALID
        }

        if (theftNo != null && theftNo.equals(cardNumber)) {
            return CardValidity.THEFT
        }

        return CardValidity.VALID
    }
}