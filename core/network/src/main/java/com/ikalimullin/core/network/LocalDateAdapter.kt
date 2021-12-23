package com.ikalimullin.core.network

import com.ikalimullin.core.constants.date.DateTimeUtils
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import java.time.LocalDate

class LocalDateAdapter : JsonAdapter<LocalDate>() {

    override fun fromJson(reader: JsonReader) =
        DateTimeUtils.convertStringToLocalDate(reader.nextString())

    override fun toJson(writer: JsonWriter, value: LocalDate?) {
        writer.value(value?.toString())
    }
}
