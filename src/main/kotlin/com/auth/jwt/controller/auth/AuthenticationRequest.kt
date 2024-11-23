package com.auth.jwt.controller.auth

data class AuthenticationRequest (
    val email: String,
    val password: String
    )
