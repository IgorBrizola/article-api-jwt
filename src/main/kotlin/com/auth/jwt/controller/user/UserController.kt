package com.auth.jwt.controller.user

import com.auth.jwt.mapper.UserMapper
import com.auth.jwt.service.UserService
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.util.UUID

@RestController
@RequestMapping("api/user")
class UserController(
    private val userService: UserService,
    private val userMapper: UserMapper
) {

    @PostMapping
    fun create(@RequestBody userRequest: UserRequest): UserResponse =
        userService.createUser(
            userRequest
        ) ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot create a user.")

    @GetMapping
    fun listAll(): List<UserResponse> =
        userService.findAll().map { userMapper.modelToResponse(it) }

    @GetMapping("{uuid}")
    fun findByUUID(@PathVariable uuid: UUID): UserResponse =
       userMapper.modelToResponse(userService.findUUID(uuid)?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot found a user."))

    @DeleteMapping("{uuid}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteByUUID(@PathVariable uuid: UUID) = userService.deleteByUUID(uuid)
}