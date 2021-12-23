package com.ikalimullin.core.constants.date

import java.time.LocalDate
import java.time.Period

object AgeUtils {

    fun getAge(localDate: LocalDate): Int = Period.between(localDate, LocalDate.now()).years
}
