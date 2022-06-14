package com.khalifa.github.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.khalifa.github.domain.usecase.UserSearchUseCase
import com.khalifa.github.presentation.entity.UserListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class UsersViewModel @javax.inject.Inject constructor(
    private val userSearchUseCase: UserSearchUseCase
) : ViewModel() {

    private val _searchResultState = MutableStateFlow(UserListState())
    val searchResultState = _searchResultState

    fun userSearch(user: String) {
        viewModelScope.launch {
            userSearchUseCase(user).cachedIn(viewModelScope).collect {
                _searchResultState.value = UserListState(
                    usersList = it
                )
            }
        }
    }
}