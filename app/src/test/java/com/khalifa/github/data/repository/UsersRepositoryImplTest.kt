package com.khalifa.github.data.repository

import com.khalifa.github.data.constant.NetworkConstants.CONNECT_EXCEPTION
import com.khalifa.github.data.constant.NetworkStatus
import com.khalifa.github.data.remote.datasource.UserDetailsRemoteDataSourceImpl
import com.khalifa.github.data.remote.entity.UserDetailsResponse
import com.khalifa.github.data.remote.service.ApiService
import com.khalifa.github.domain.entity.UserDomainEntities
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.ArgumentMatchers.any

class UsersRepositoryImplTest {
    private val userDetailsRemoteDataSource: UserDetailsRemoteDataSourceImpl = mockk(relaxed = true)
    private val apiService: ApiService = mockk(relaxed = true)

    private val usersRepositoryImpl =
        UsersRepositoryImpl(apiService, userDetailsRemoteDataSource)

    @Test
    fun `test UserDetailsResponse Success when calling getUserDetails`() {
        runBlocking {
            val login = "test"
            val userDetailsNetworkResponse: NetworkStatus<UserDetailsResponse> =
                NetworkStatus.Success(
                    UserDetailsResponse(
                        login = login,
                        id = 1,
                        name = "test",
                        avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                        url = "https ://api.github.com/users/test",
                        bio = "Android Developer",
                        publicRepos = 20,
                        publicGists = 2,
                        followers = 21,
                        following = 5,
                        type = "User",
                        siteAdmin = false,
                        gravatarId = null,
                        company = null,
                        htmlUrl = any(),
                        twitterUsername = null,
                        followersUrl = any(),
                        followingUrl = any(),
                        gistsUrl = any(),
                        starredUrl = any(),
                        subscriptionsUrl = any(),
                        organizationsUrl = any(),
                        reposUrl = any(),
                        eventsUrl = any(),
                        receivedEventsUrl = any(),
                        nodeId = any(),
                        blog = null,
                        location = null,
                        email = null,
                        hireable = any(),
                        createdAt = any(),
                        updatedAt = any()
                    )
                )
            val expected = UserDomainEntities.UserDomainItem(
                login,
                1,
                "https://avatars.githubusercontent.com/u/1?v=4",
                name = "test",
                followers = 21,
                following = 5,
                repos = 20,
                company = "",
                bio = "Android Developer"
            )

            coEvery { userDetailsRemoteDataSource.getUserDetails(login) } returns userDetailsNetworkResponse

            Assert.assertEquals(expected, usersRepositoryImpl.getUsersDetails(login))
        }
    }

    @Test
    fun `test UserDetailsResponse Connection failed response when calling getUserDetails`() {
        runBlocking {
            val userDetailsNetworkResponse: NetworkStatus<UserDetailsResponse> =
                NetworkStatus.Error(CONNECT_EXCEPTION)
            val expected = UserDomainEntities.Failure(CONNECT_EXCEPTION)
            val login = "test"
            coEvery { userDetailsRemoteDataSource.getUserDetails(login) } returns userDetailsNetworkResponse
            Assert.assertEquals(expected, usersRepositoryImpl.getUsersDetails(login))
        }
    }
}