package com.auth.jwt.service

import com.auth.jwt.model.User
import com.auth.jwt.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun createUser(user: User): User? {
        val found = userRepository.findByEmail(user.email)

        return if (found == null) {
            userRepository.save(user)
            user
        } else null
    }

    fun findUUID(uuid: UUID): User? = userRepository.findByUUID(uuid)

    fun findAll(): List<User> = userRepository.findAll()

    fun deleteByUUID(uuid: UUID): Boolean = userRepository.deletByUUID(uuid)
}