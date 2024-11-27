package com.auth.jwt.service

import com.auth.jwt.config.JwtProperties
import com.auth.jwt.controller.auth.AuthenticationRequest
import com.auth.jwt.controller.auth.AuthenticationResponse
import com.auth.jwt.controller.auth.RefreshTokenRequest
import com.auth.jwt.controller.auth.TokenResponse
import com.auth.jwt.model.RefreshToken
import com.auth.jwt.repository.RefreshTokenRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.Date

@Service
class AuthenticationService(
    private val authManager: AuthenticationManager,
    private val userDetailsService: CustomDetailsService,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository,
) {
    fun authentication(authRequest: AuthenticationRequest): AuthenticationResponse {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.email,
                authRequest.password
            )
        )

        val user = userDetailsService.loadUserByUsername(authRequest.email)

        val accessToken = generateAccessToken(user)

        val refreshToken = generateRefreshToken(user)

        updateOrCreateRefreshToken(user.username, refreshToken)

        return AuthenticationResponse(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    private fun updateOrCreateRefreshToken(username: String, refreshToken: String) {
        val existingToken = refreshTokenRepository.findByUsername(username)

        if (existingToken != null) {
            val updateToken = existingToken.copy(token = refreshToken)
            refreshTokenRepository.save(updateToken)
        } else {
            val refreshNewToken = RefreshToken(
                token = refreshToken,
                username = username
            )
            refreshTokenRepository.save(refreshNewToken)
        }
    }

    private fun generateRefreshToken(user: UserDetails) = tokenService.generate(
        userDetails = user,
        expirationDate = Date(System.currentTimeMillis() + jwtProperties.refreshTokenExpiration)
    )

    private fun generateAccessToken(user: UserDetails) = tokenService.generate(
        userDetails = user,
        expirationDate = Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)
    )

    fun refreshAccessToken(tokenRequest: RefreshTokenRequest): TokenResponse? {
        val extractEmail = tokenService.extractEmail(tokenRequest.token)
        return extractEmail?.let { email ->
            val currentUserDetails = userDetailsService.loadUserByUsername(email)
            val refreshTokenUsername = refreshTokenRepository.findUsernameByToken(tokenRequest.token)

            if (!tokenService.isExpired(tokenRequest.token) && currentUserDetails.username == refreshTokenUsername)
                TokenResponse(token = generateAccessToken(currentUserDetails))
            else null
        }
    }
}