package com.khalifa.github.data.remote.datasource

import com.khalifa.github.data.constant.NetworkStatus
import com.khalifa.github.data.remote.entity.UserDetailsResponse
import com.khalifa.github.data.remote.service.ApiService
import com.khalifa.github.data.util.safeApiCall

class UserDetailsRemoteDataSourceImpl(private val apiService: ApiService) {
    suspend fun getUserDetails(userId: String): NetworkStatus<UserDetailsResponse> =
        safeApiCall { apiService.getUsersDetails(userId) }
}