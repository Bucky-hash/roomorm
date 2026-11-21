package com.example.studentapp.network

import com.example.studentapp.model.Student

/** Data Transfer Object cho JSON từ REST API (demo dùng jsonplaceholder /users). */
data class StudentDto(
    val id: Int,
    val name: String,
    val username: String? = null,
    val email: String? = null
)

/**
 * Map DTO -> Entity. id = 1000 + id từ server để các lần đồng bộ sau
 * ghi đè (REPLACE) thay vì tạo bản ghi trùng lặp.
 */
fun StudentDto.toEntity(): Student = Student(
    id = 1000 + id,
    fullName = name,
    studentCode = "SV" + id.toString().padStart(4, '0'),
    email = email ?: "${(username ?: "sv$id").lowercase()}@ute.udn.vn",
    gpa = 2.5 + (id % 15) / 10.0,
    major = "Công Nghệ Thông Tin"
)
