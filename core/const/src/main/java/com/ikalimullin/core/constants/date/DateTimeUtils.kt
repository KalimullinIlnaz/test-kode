package com.ikalimullin.core.constants.date

import timber.log.Timber
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeParseException

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
}
