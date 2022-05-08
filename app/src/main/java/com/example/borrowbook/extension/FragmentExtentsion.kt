package com.example.borrowbook.extension

import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

fun Fragment.getCurrentDate(date: Date): String {
    return date.toString("yyyy-MM-dd HH:mm:ss")
}

fun Fragment.getDueDate(date: Date): String {
    val cal = Calendar.getInstance()
    cal.time = date
    cal.add(Calendar.DATE, 7)
    return cal.time.toString("yyyy-MM-dd HH:mm:ss")
}

fun String.formatDateText(): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val date = formatter.parse(this)
    val end_date = date.toString("dd MMM yyyy")
    return end_date
}