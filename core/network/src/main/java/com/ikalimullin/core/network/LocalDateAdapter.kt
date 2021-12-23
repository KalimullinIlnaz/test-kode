package com.ikalimullin.core.network

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import timber.log.Timber
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

private const val TIME_PATTERN = "yyyy-MM-dd"
private val timeFormatter = DateTimeFormatter.ofPattern(TIME_PATTERN)

class LocalDateAdapter : JsonAdapter<LocalDate>() {

    override fun fromJson(reader: JsonReader) = try {
        LocalDate.parse(reader.nextString(), timeFormatter)
    } catch (e: DateTimeParseException) {
        Timber.e(e)
        null
    }

    override fun toJson(writer: JsonWriter, value: LocalDate?) {
        writer.value(value?.toString())
    }
}
