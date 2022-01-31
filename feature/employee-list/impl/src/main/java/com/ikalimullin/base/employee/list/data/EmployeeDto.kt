package com.ikalimullin.base.employee.list.data

import com.ikalimullin.entity.employee.Department
import com.ikalimullin.entity.employee.Employee
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDate

@JsonClass(generateAdapter = true)
internal data class EmployeeDto(
    @Json(name = "items") val items: List<Employee>
) {

    @JsonClass(generateAdapter = true)
    data class Employee(
        @Json(name = "id") val id: String,
        @Json(name = "avatarUrl") val avatarUrl: String,
        @Json(name = "firstName") val firstName: String,
        @Json(name = "lastName") val lastName: String,
        @Json(name = "userTag") val userTag: String,
        @Json(name = "department") val department: String?,
        @Json(name = "position") val position: String,
        @Json(name = "birthday") val birthday: LocalDate?,
        @Json(name = "phone") val phone: String
    )
}

internal fun EmployeeDto.Employee.toDomain() = Employee(
    id = id,
    avatarUrl = avatarUrl,
    firstName = firstName,
    lastName = lastName,
    userTag = userTag,
    department = department?.let { department ->
        Department.values().find { department == it.name.lowercase() }
    },
    position = position,
    birthday = birthday,
    phone = phone
)
