package com.auth.jwt.repository

import com.auth.jwt.model.RefreshToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface RefreshTokenRepository : JpaRepository<RefreshToken, UUID>{

    @Query("SELECT r.username FROM RefreshToken r WHERE r.token = ?1")
    fun findUsernameByToken(token: String): String?

}