package com.example.tddpracticebymadvirus.chap07.autodebit.domain

import com.example.tddpracticebymadvirus.chap07.autodebit.persistence.entity.AutoDebitInfo

interface AutoDebitInfoRepository {
    fun save(info: AutoDebitInfo)

    fun findOne(userId: String): AutoDebitInfo?
}