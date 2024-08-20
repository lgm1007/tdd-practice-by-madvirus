package com.example.tddpracticebymadvirus.chap07.domain

import com.example.tddpracticebymadvirus.chap07.persistence.entity.AutoDebitInfo

interface AutoDebitInfoRepository {
    fun save(info: AutoDebitInfo)

    fun findOne(userId: String): AutoDebitInfo?
}