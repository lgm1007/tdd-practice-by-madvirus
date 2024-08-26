package com.example.tddpracticebymadvirus.chap08.testable

import com.example.tddpracticebymadvirus.chap08.subscription.Times
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.time.format.DateTimeFormatter

class DailyBatchLoader {
    private var basePath = "."
    private var times = Times()

    fun setTimes(times: Times) {
        this.times = times
    }

    fun setBasePath(basePath: String) {
        this.basePath = basePath
    }

    fun load(): Int {
        val date = times.today()
        val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        val batchPath = Paths.get(basePath, date.format(formatter), "batch.txt")

        try {
            return Files.lines(batchPath).count().toInt()
        } catch (e: IOException) {
            throw RuntimeException()
        }
    }
}