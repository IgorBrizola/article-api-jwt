package com.auth.jwt.mapper

import com.auth.jwt.controller.article.ArticleResponse
import com.auth.jwt.model.Article
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING
)
interface ArticleMapper {
    fun modelToResponse(article: Article): ArticleResponse
    fun responseToResponse(articleResponse: ArticleResponse): Article
}