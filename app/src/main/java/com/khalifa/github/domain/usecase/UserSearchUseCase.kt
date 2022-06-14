package com.khalifa.github.domain.usecase

import androidx.paging.PagingData
import com.khalifa.github.domain.entity.UserDomainEntities
import com.khalifa.github.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserSearchUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend operator fun invoke(user: String): Flow<PagingData<UserDomainEntities.UserDomainItem>> {
        return usersRepository.getUsers(user)
    }
}