package com.auth.jwt.repository

import com.auth.jwt.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserRepository: JpaRepository<User, UUID> {
    fun findByEmail(email: String): User?
}