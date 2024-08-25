package com.example.tddpracticebymadvirus.chap08.testable

import com.example.tddpracticebymadvirus.chap08.payinfo.PayInfo
import java.nio.file.Files
import java.nio.file.Paths

class PaySync {
    private var filePath = "D:\\data\\pay\\cp0001.csv"

    fun setFilePath(filePath: String) {
        this.filePath = filePath
    }

    fun sync() {
        val path = Paths.get(filePath)
        val payInfos = Files.lines(path)
            .map { line ->
                val data = line.split(",")
                return@map PayInfo(data[0], data[1], data[2].toInt())
            }
            .toList()

        payInfos.forEach { payInfoDao.insert(it) }
    }
}