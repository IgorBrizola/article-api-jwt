package com.auth.jwt.controller.user

import java.util.UUID

data class UserResponse(
    val uuid: UUID,
    val email: String,
    val password: String
)