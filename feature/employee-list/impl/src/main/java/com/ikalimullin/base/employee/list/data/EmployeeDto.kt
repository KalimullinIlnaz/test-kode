package com.ikalimullin.base.employee.list.data

import android.annotation.SuppressLint
import com.ikalimullin.entity.employee.Department
import com.ikalimullin.entity.employee.Employee
import java.time.LocalDateTime

internal data class EmployeeDto(
    val id: String,
    val avatarUrl: String,
    val firstName: String,
    val lastName: String,
    val userTag: String,
    val department: String,
    val position: String,
    val birthdate: String,
    val phone: String
)

@SuppressLint("NewApi")
internal fun EmployeeDto.toDomain() = Employee(
    id = id,
    avatarUrl = avatarUrl,
    firstName = firstName,
    lastName = lastName,
    userTag = userTag,
    department = Department.valueOf(department),
    position = position,
    birthdate = LocalDateTime.parse(birthdate),
    phone = phone
)
