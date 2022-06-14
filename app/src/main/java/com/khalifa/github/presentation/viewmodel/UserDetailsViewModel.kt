package com.khalifa.github.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khalifa.github.domain.entity.UserDomainEntities
import com.khalifa.github.domain.usecase.GetUserDetailsUseCase
import com.khalifa.github.presentation.entity.UserDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class UserDetailsViewModel @javax.inject.Inject constructor(
    private val getUserDetailsUseCase: GetUserDetailsUseCase
) : ViewModel() {

    private val _userDetailsState = MutableStateFlow(UserDetailsState())
    val userDetailsState = _userDetailsState

    fun getUserDetails(user: String) {
        viewModelScope.launch {
            _userDetailsState.value = UserDetailsState(inProgress = true)
            _userDetailsState.value = when (val result = getUserDetailsUseCase(user)) {
                is UserDomainEntities.UserDomainItem -> UserDetailsState(
                    result
                )
                is UserDomainEntities.Failure -> UserDetailsState(
                    errorMessage = result.errorText
                )
            }
        }
    }
}