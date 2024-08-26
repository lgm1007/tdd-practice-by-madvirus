package com.example.tddpracticebymadvirus.chap08.nontestable

import com.example.tddpracticebymadvirus.chap08.payinfo.PayInfo
import com.example.tddpracticebymadvirus.chap08.payinfo.PayInfoDao
import java.nio.file.Files
import java.nio.file.Paths

class PaySync {
    // 의존 대상을 직접 생성하는 점 또한 테스트를 어렵게 만드는 요인
    private val payInfoDao = PayInfoDao()

    fun sync() {
        val path = Paths.get("D:\\data\\pay\\cp0001.csv")
        val payInfos = Files.lines(path)
            .map { line ->
                val data = line.split(",")
                return@map PayInfo(data[0], data[1], data[2].toInt())
            }
            .toList()

        payInfos.forEach { payInfoDao.insert(it) }
    }
}