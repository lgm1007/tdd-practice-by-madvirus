package com.example.tddpracticebymadvirus.chap07.autodebit.persistence

import com.example.tddpracticebymadvirus.chap07.autodebit.domain.AutoDebitInfoRepository
import com.example.tddpracticebymadvirus.chap07.autodebit.persistence.entity.AutoDebitInfo

class MemoryAutoDebitInfoRepository(
    private val infos: HashMap<String, AutoDebitInfo> = HashMap()
) : AutoDebitInfoRepository {
    override fun save(info: AutoDebitInfo) {
        infos[info.userId] = info
    }

    override fun findOne(userId: String): AutoDebitInfo? {
        return infos[userId]
    }
}