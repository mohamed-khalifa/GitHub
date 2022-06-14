package com.khalifa.github.presentation.entity

import androidx.paging.PagingData
import com.khalifa.github.domain.entity.UserDomainEntities

data class UserListState(
    val usersList: PagingData<UserDomainEntities.UserDomainItem>? = null,
)
