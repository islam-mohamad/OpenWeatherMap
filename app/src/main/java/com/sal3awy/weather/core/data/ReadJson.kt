package com.sal3awy.weather.core.data

import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

fun loadJSONFromAsset(inputStream: InputStream): String? {
    return try {
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        String(buffer, Charset.forName("UTF-8"))
    } catch (ex: IOException) {
        ex.printStackTrace()
        null
    }
}