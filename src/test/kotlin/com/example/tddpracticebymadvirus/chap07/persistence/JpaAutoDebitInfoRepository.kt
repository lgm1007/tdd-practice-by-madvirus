package com.example.tddpracticebymadvirus.chap07.persistence

import com.example.tddpracticebymadvirus.chap07.domain.AutoDebitInfoRepository
import com.example.tddpracticebymadvirus.chap07.persistence.entity.AutoDebitInfo

class JpaAutoDebitInfoRepository : AutoDebitInfoRepository {
    override fun save(info: AutoDebitInfo) {

    }

    override fun findOne(userId: String): AutoDebitInfo? {
        return null
    }
}