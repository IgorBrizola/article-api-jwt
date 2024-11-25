package com.auth.jwt.controller.auth

import com.auth.jwt.service.AuthenticationService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("api/auth")
class AuthController(
    private val authenticationService: AuthenticationService,
) {

    @PostMapping
    fun authentication(@RequestBody authRequest: AuthenticationRequest): AuthenticationResponse =
        authenticationService.authentication(authRequest)

    @PostMapping("refresh")
    fun refreshAccessToken(
        @RequestBody tokenRequest: RefreshTokenRequest
    ): TokenResponse =
        authenticationService.refreshAccessToken(tokenRequest)
            ?: throw ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid refresh token!")
}