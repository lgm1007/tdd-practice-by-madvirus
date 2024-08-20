package com.example.tddpracticebymadvirus.chap07.persistence.entity

import com.example.tddpracticebymadvirus.chap07.domain.AutoDebitInfoRepository

class JpaAutoDebitInfoRepository : AutoDebitInfoRepository {
    override fun save(info: AutoDebitInfo) {

    }

    override fun findOne(userId: String): AutoDebitInfo? {
        return null
    }
}