package com.khalifa.github.data.remote.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.khalifa.github.data.remote.service.ApiService
import com.khalifa.github.domain.entity.UserDomainEntities

private const val STARTING_PAGE_INDEX: Int = 1

class UsersRemoteDataSourceImpl(private val apiService: ApiService, private val user: String) :
    PagingSource<Int, UserDomainEntities.UserDomainItem>() {

    override fun getRefreshKey(state: PagingState<Int, UserDomainEntities.UserDomainItem>): Int? =
        state.anchorPosition


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserDomainEntities.UserDomainItem> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = apiService.getUsers(user, page)
            val users = response.items?.map {
                UserDomainEntities.UserDomainItem(
                    userName = it.login ?: "",
                    id = it.id,
                    avatarUrl = it.avatarUrl ?: ""
                )
            } ?: emptyList()
            LoadResult.Page(
                users, prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (response.items.isNullOrEmpty()) null else page + 1
            )

        } catch (exception: Throwable) {
            LoadResult.Error(exception)
        }
    }
}


