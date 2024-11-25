package com.auth.jwt.mapper

import com.auth.jwt.controller.user.UserRequest
import com.auth.jwt.controller.user.UserResponse
import com.auth.jwt.model.User
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING
)
interface UserMapper {
    fun modelToResponse(user: User): UserResponse
    fun responseToModel(userResponse: UserResponse): User

    fun modelToRequest(user: User): UserRequest
    fun requestToModel(userRequest: UserRequest): User


}