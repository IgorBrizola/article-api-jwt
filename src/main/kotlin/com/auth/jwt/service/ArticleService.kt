package com.auth.jwt.service

import com.auth.jwt.controller.article.ArticleRequestUpdate
import com.auth.jwt.controller.article.ArticleResponse
import com.auth.jwt.mapper.ArticleMapper
import com.auth.jwt.model.Article
import com.auth.jwt.repository.ArticleRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ArticleService(
    private val articleRepository: ArticleRepository,
    private val articleMapper: ArticleMapper
) {
    fun findAll(): List<ArticleResponse> = articleRepository.findAll().map { articleMapper.modelToResponse(it) }

    fun updateArticle(id: UUID, requestUpdate: ArticleRequestUpdate): ArticleResponse? {
       return articleRepository.findById(id).map {
            val save = articleRepository.save(Article(
                id = it.id,
                title = requestUpdate.title ?: it.title,
                content = requestUpdate.content ?: it.content
            ))
            articleMapper.modelToResponse(save)
        }.orElseGet(null)
    }

}