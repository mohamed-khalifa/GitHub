package com.khalifa.github.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.khalifa.github.data.constant.NetworkConstants.GENERAL_ERROR
import com.khalifa.github.data.remote.datasource.UserDetailsRemoteDataSourceImpl
import com.khalifa.github.data.remote.datasource.UsersRemoteDataSourceImpl
import com.khalifa.github.data.remote.service.ApiService
import com.khalifa.github.domain.entity.UserDomainEntities
import com.khalifa.github.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val PAGE_SIZE = 30

class UsersRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val userDetailsRemoteDataSource: UserDetailsRemoteDataSourceImpl
) : UsersRepository {
    override suspend fun getUsers(user: String): Flow<PagingData<UserDomainEntities.UserDomainItem>> =
        Pager(PagingConfig(pageSize = PAGE_SIZE)) {
            UsersRemoteDataSourceImpl(apiService = apiService, user)
        }.flow


    override suspend fun getUsersDetails(user: String): UserDomainEntities {
        val response = userDetailsRemoteDataSource.getUserDetails(user)
        return response.data?.let { userItem ->
            UserDomainEntities.UserDomainItem(
                userItem.name ?: "",
                userItem.id ?: -1,
                userItem.avatarUrl ?: "",
                name = userItem.name ?: "",
                followers = userItem.followers ?: 0,
                following = userItem.following ?: 0,
                repos = userItem.publicRepos ?: 0,
                company = userItem.company ?: "",
                bio = userItem.bio ?: ""
            )
        } ?: run {
            UserDomainEntities.Failure(response.errorMessage ?: GENERAL_ERROR)
        }
    }

}

