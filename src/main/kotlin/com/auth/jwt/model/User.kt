package com.auth.jwt.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    @Column(name = "email")
    val email: String,
    @Column(name = "password")
    val password: String,
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    val role: Role
)

enum class Role {
    USER, ADMIN
}