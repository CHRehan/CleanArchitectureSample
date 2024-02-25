package com.clean.architecture.mockserver

import android.content.Context
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

/**
 * Created by Rehan Sarwar on 24/07/2022.
 */
object AssetReaderUtil {
    fun asset(context: Context, assetPath: String): String {
        try {
            val inputStream = context.assets.open("network_response/$assetPath")
            return inputStreamToString(inputStream, "UTF-8")
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    private fun inputStreamToString(inputStream: InputStream, charsetName: String): String {
        val builder = StringBuilder()
        val reader = InputStreamReader(inputStream, charsetName)
        reader.readLines().forEach {
            builder.append(it)
        }
        return builder.toString()
    }
}
