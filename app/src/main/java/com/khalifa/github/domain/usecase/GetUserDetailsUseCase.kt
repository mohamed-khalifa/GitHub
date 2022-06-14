package com.khalifa.github.domain.usecase

import com.khalifa.github.domain.entity.UserDomainEntities
import com.khalifa.github.domain.repository.UsersRepository
import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend operator fun invoke(user: String): UserDomainEntities {
        return usersRepository.getUsersDetails(user)
    }
}