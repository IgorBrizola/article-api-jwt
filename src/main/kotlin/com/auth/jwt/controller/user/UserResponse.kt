package com.auth.jwt.controller.user

import java.util.UUID

data class UserResponse(
    val id: UUID,
    val email: String,
    val password: String
)