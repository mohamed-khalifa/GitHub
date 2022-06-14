package com.khalifa.github.domain.entity

sealed class UserDomainEntities {
    data class UserDomainItem(
        val userName: String,
        val id: Int,
        val avatarUrl: String,
        val name: String = "",
        val followers: Int = 0,
        val following: Int = 0,
        val repos: Int = 0,
        val company: String = "",
        val bio: String = ""
    ) : UserDomainEntities()

    data class Failure(val errorText: String) : UserDomainEntities()
}

