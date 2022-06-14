package com.khalifa.github.data.remote.service

import com.khalifa.github.data.remote.entity.UserDetailsResponse
import com.khalifa.github.data.remote.entity.UsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    suspend fun getUsers(
        @Query("q") user: String,
        @Query("page") page: Int
    ): UsersResponse

    @GET("users/{user}")
    suspend fun getUsersDetails(
        @Path("user") user: String
    ): Response<UserDetailsResponse>
}