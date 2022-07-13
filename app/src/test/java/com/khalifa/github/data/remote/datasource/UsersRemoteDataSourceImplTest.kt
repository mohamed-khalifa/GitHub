package com.khalifa.github.data.remote.datasource

import androidx.paging.PagingSource
import com.khalifa.github.data.constant.NetworkConstants
import com.khalifa.github.data.remote.entity.UsersResponse
import com.khalifa.github.data.remote.service.ApiService
import com.khalifa.github.domain.entity.UserDomainEntities
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.ArgumentMatchers.any

class UsersRemoteDataSourceImplTest {

    private val apiService: ApiService = mockk(relaxed = true)
    private val login = "test"

    @Test
    fun `test  UserDomainItem returned  when calling UsersRemoteDataSourceImpl pagingSource load`() {

        val users = listOf(
            UsersResponse.User(
                login = login,
                id = 1,
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                url = "https://api.github.com/users/test",
                type = "User",
                siteAdmin = false,
                gravatarId = null,
                htmlUrl = any(),
                followingUrl = any(),
                followersUrl = any(),
                gistsUrl = any(),
                starredUrl = any(),
                subscriptionsUrl = any(),
                organizationsUrl = any(),
                reposUrl = any(),
                eventsUrl = any(),
                receivedEventsUrl = any(),
                nodeId = any(),
                score = 1
            )
        )

        runBlocking {
            coEvery { apiService.getUsers(login, 1) } returns UsersResponse(1, true, users, null)

            val pagingSource = UsersRemoteDataSourceImpl(apiService, login)

            assertEquals(
                pagingSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 1,
                        placeholdersEnabled = false
                    )
                ),
                PagingSource.LoadResult.Page(
                    listOf(
                        UserDomainEntities.UserDomainItem(
                            userName = login,
                            id = 1,
                            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4"
                        )
                    ), null, 2
                )
            )
        }
    }


    @Test
    fun `test an Error returned  when calling UsersRemoteDataSourceImpl pagingSource load fail`() {
        val exception = Throwable(NetworkConstants.SOCKET_TIME_OUT_EXCEPTION)
        runBlocking {
            coEvery { apiService.getUsers(login, 1) } throws exception

            val pagingSource = UsersRemoteDataSourceImpl(apiService, login)

            assertEquals(
                pagingSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 1,
                        placeholdersEnabled = false
                    )
                ),
                PagingSource.LoadResult.Error<String, Exception>(exception)
            )
        }
    }
}
