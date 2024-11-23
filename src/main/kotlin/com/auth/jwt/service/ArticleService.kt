package com.auth.jwt.service

import com.auth.jwt.model.Article
import com.auth.jwt.repository.ArticleRepository
import org.springframework.stereotype.Service

@Service
class ArticleService(
    private val articleRepository: ArticleRepository
) {
    fun findAll(): List<Article> = articleRepository.findAll()
}