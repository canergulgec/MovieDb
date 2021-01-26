package com.android.test.utils

import androidx.test.platform.app.InstrumentationRegistry
import com.android.test.MovieTestApp
import java.io.IOException
import java.io.InputStreamReader

object FileReader {
    fun readTestResourceFile(fileName: String): String {
        try {
            val inputStream = (InstrumentationRegistry.getInstrumentation().targetContext
                .applicationContext as MovieTestApp).assets.open(fileName)
            val builder = StringBuilder()
            val reader = InputStreamReader(inputStream, "UTF-8")
            reader.readLines().forEach {
                builder.append(it)
            }
            return builder.toString()
        } catch (e: IOException) {
            throw e
        }
    }
}