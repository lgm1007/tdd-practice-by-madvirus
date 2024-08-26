package com.example.tddpracticebymadvirus.chap07.autodebit.persistence

import com.example.tddpracticebymadvirus.chap07.autodebit.domain.AutoDebitInfoRepository
import com.example.tddpracticebymadvirus.chap07.autodebit.persistence.entity.AutoDebitInfo

class StubAutoDebitInfoRepository : AutoDebitInfoRepository {
    override fun save(info: AutoDebitInfo) {

    }

    override fun findOne(userId: String): AutoDebitInfo? {
        return null
    }
}