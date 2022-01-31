package com.ikalimullin.core.constants.date

import java.time.Instant
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeParseException
import timber.log.Timber

object DateTimeUtils {

    fun convertLocalDateToString(localDate: LocalDate): String =
        localDate.format(DateTimeConst.timeFormatter)

    fun convertStringToLocalDate(localDate: String): LocalDate? = try {
        LocalDate.parse(localDate, DateTimeConst.timeFormatter)
    } catch (e: DateTimeParseException) {
        Timber.e(e)
        null
    }

    fun convertLocalDateToLong(localDate: LocalDate): Long =
        localDate.atStartOfDay(DateTimeConst.zoneId).toInstant().toEpochMilli()

    fun convertLongToLocalDate(localDate: Long): LocalDate =
        Instant.ofEpochMilli(localDate).atZone(DateTimeConst.zoneId).toLocalDate()

    fun createDateWithCurrentYear(now: LocalDate, date: LocalDate): LocalDate = LocalDate.of(
        now.year,
        date.month,
        date.dayOfMonth
    )

    fun Month.getMonthNumber(): Int = value - 1
}
