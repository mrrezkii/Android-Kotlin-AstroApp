package com.kedirilagi.astro.extension

import java.util.*

fun monthExtension(year: Int, month: Int, dayOfMonth: Int): Comparable<*> {
    val calendar: Calendar = Calendar.getInstance()
    calendar.set(year, month, dayOfMonth)

    val month = when (val months = calendar.get(Calendar.MONTH)) {
        0 -> "Januari"
        1 -> "Februari"
        2 -> "Maret"
        3 -> "April"
        4 -> "Mei"
        5 -> "Juni"
        6 -> "Juli"
        7 -> "Agustus"
        8 -> "September"
        9 -> "Oktober"
        10 -> "November"
        11 -> "Desember"
        else -> months
    }

    return month
}

fun dayExtension(year: Int, month: Int, dayOfMonth: Int): Comparable<*> {
    val calendar: Calendar = Calendar.getInstance()
    calendar.set(year, month, dayOfMonth)

    val day = when (val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)) {
        1 -> "Minggu"
        2 -> "Senin"
        3 -> "Selasa"
        4 -> "Rabu"
        5 -> "Kamis"
        6 -> "Jumat"
        7 -> "Sabtu"
        else -> dayOfWeek
    }

    return day
}