package com.example.tddpracticebymadvirus.chap08.nontestable

import com.example.tddpracticebymadvirus.chap08.payinfo.PayInfo
import com.example.tddpracticebymadvirus.chap08.payinfo.PayInfoDao
import java.nio.file.Files
import java.nio.file.Paths

class PaySync {
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