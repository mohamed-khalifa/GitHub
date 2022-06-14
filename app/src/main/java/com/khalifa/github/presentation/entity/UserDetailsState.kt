package com.khalifa.github.presentation.entity

import com.khalifa.github.domain.entity.UserDomainEntities

data class UserDetailsState(
    val userDetails: UserDomainEntities.UserDomainItem? = null,
    val errorMessage: String = "",
    val inProgress: Boolean = false
)
