package com.auth.jwt.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "article")
data class Article(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID,
    @Column(name = "title")
    val title: String,
    @Column(name = "content")
    val content: String
)