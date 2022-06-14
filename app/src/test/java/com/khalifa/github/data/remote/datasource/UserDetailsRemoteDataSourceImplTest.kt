package com.khalifa.github.data.remote.datasource

import com.khalifa.github.data.remote.service.ApiService
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class UserDetailsRemoteDataSourceImplTest {

    private val apiService: ApiService = mockk(relaxed = true)

    @Test
    fun `test api getUsersDetails is called from UserDetailsRemoteDataSourceImpl`() {
        val remoteDataSource = UserDetailsRemoteDataSourceImpl(apiService)
        runBlocking {
            remoteDataSource.getUserDetails("test")
            coVerify(exactly = 1) { apiService.getUsersDetails("test") }
        }
    }

}