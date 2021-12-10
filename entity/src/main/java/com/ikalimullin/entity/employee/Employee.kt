package com.ikalimullin.entity.employee

import java.time.LocalDateTime

data class Employee(
    val id: String,
    val avatarUrl: String,
    val firstName: String,
    val lastName: String,
    val userTag: String,
    val department: Department,
    val position: String,
    val birthdate: LocalDateTime,
    val phone: String
)
