package com.auth.jwt.repository

import com.auth.jwt.model.Article
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ArticleRepository : JpaRepository<Article, UUID>