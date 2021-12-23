package com.ikalimullin.core.constants.date

import java.time.ZoneId
import java.time.format.DateTimeFormatter

internal object DateTimeConst {
    private const val TIME_PATTERN = "yyyy-MM-dd"
    val timeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern(TIME_PATTERN)
    val zoneId = ZoneId.systemDefault()
}
