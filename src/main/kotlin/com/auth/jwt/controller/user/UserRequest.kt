package com.auth.jwt.controller.user

import com.auth.jwt.model.Role

data class UserRequest(
    val email: String,
    val password: String,
    val role: Role = Role.USER
)