package org.chosun.dodamduck.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

object DateUtil {
    fun String.formatDateDiff(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val inputDate = LocalDateTime.parse(this, formatter)
        val currentDate = LocalDateTime.now()

        val daysDiff = ChronoUnit.DAYS.between(inputDate, currentDate)
        val hoursDiff = ChronoUnit.HOURS.between(inputDate, currentDate)
        val minutesDiff = ChronoUnit.MINUTES.between(inputDate, currentDate)

        return when {
            daysDiff > 0 -> "${daysDiff}일 전"
            hoursDiff > 0 -> "${hoursDiff}시간 전"
            minutesDiff > 0 -> "${minutesDiff}분 전"
            else -> "방금 전"
        }
    }
}