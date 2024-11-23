package com.auth.jwt.controller.auth

data class AuthenticationResponse(
    val accessToken: String,
    val refreshToken: String
)
