package com.xoxoer.newspocket.extensions

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun String.toReadableLocalDateTime(pattern: String): String {
    val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SS'Z'")
    val output = SimpleDateFormat(pattern, Locale.US)

    return try {
        output.format(input.parse(this)!!)
    } catch (e: ParseException) {
        e.printStackTrace()
        ""
    }
}