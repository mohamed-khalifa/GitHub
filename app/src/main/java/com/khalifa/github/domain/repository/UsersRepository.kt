package com.khalifa.github.domain.repository

import androidx.paging.PagingData
import com.khalifa.github.domain.entity.UserDomainEntities
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun getUsers(user: String): Flow<PagingData<UserDomainEntities.UserDomainItem>>
    suspend fun getUsersDetails(user: String): UserDomainEntities
}