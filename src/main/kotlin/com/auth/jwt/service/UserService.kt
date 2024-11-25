package com.auth.jwt.service

import com.auth.jwt.controller.user.UserRequest
import com.auth.jwt.controller.user.UserResponse
import com.auth.jwt.mapper.UserMapper
import com.auth.jwt.model.User
import com.auth.jwt.repository.UserRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
    private val encoder: PasswordEncoder
) {
    fun createUser(userRequest: UserRequest): UserResponse? {
        val user = userMapper.requestToModel(userRequest)
        val found = userRepository.findByEmail(user.email)

        return if (found == null) {
            val encodedPassword = encoder.encode(user.password)
            val newUser = user.copy(password = encodedPassword)
            userRepository.save(newUser)
            userMapper.modelToResponse(newUser)
        } else null
    }

    fun findUUID(uuid: UUID): User? = userRepository.findById(uuid).orElseThrow { NotFoundException() }

    fun findAll(): List<User> = userRepository.findAll()

    fun deleteByUUID(uuid: UUID) = userRepository.deleteById(uuid)
}