package com.ikalimullin.core.constants.date

import timber.log.Timber
import java.time.Instant
import java.time.LocalDate
import java.time.Month
import java.time.Year
import java.time.format.DateTimeParseException

object DateTimeUtils {
    private const val FEBRUARY_29 = 29
    private const val FEBRUARY_28 = 28

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

    fun createDateWithCurrentYear(date: LocalDate): LocalDate {
        val now = LocalDate.now()
        val day = date.dayOfMonth
        val month = date.month

        return if (month == Month.FEBRUARY && day == FEBRUARY_29 && !Year.of(now.year).isLeap) {
            // Ставим принудительно дату дня рождения 28 февраля, так как 29 феврял в этом году нет
            LocalDate.of(
                now.year,
                month,
                FEBRUARY_28
            )
        } else {
            LocalDate.of(
                now.year,
                month,
                day
            )
        }
    }

    fun Month.getMonthNumber(): Int = value - 1
}
