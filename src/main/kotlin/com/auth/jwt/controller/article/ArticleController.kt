package com.auth.jwt.controller.article

import com.auth.jwt.service.ArticleService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.util.UUID

@RestController
@RequestMapping("api/article")
class ArticleController(
    private val articleService: ArticleService,
) {
    @GetMapping
    fun listAll(): List<ArticleResponse> = articleService.findAll()

    @PatchMapping("{id}")
    fun updateArticle(
        @PathVariable id: UUID,
        @RequestBody requestUpdate: ArticleRequestUpdate
    ): ArticleResponse = articleService.updateArticle(id, requestUpdate) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found")
}