package com.khalifa.github.presentation.viewmodel

import com.khalifa.github.data.constant.NetworkConstants.SOCKET_TIME_OUT_EXCEPTION
import com.khalifa.github.domain.entity.UserDomainEntities
import com.khalifa.github.domain.usecase.GetUserDetailsUseCase
import com.khalifa.github.presentation.entity.UserDetailsState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class UserDetailsViewModelTest : ViewModelTest() {
    private val getUserDetailsUseCase: GetUserDetailsUseCase = mockk(relaxed = true)
    private val viewModel = UserDetailsViewModel(getUserDetailsUseCase)

    @Test
    fun `test userDetailsState is notified with user details when calling getUserDetails`() {
        val username = "test"
        val userDomainItem = UserDomainEntities.UserDomainItem(
            username,
            1,
            "https://avatars.githubusercontent.com/u/1?v=4",
            name = "test",
            followers = 21,
            following = 5,
            repos = 20,
            company = "",
            bio = "Android Developer"
        )
        val expected = UserDetailsState(
            userDomainItem
        )
        runBlocking {
            coEvery { getUserDetailsUseCase.invoke(username) } returns userDomainItem
            viewModel.getUserDetails(username)
            assertEquals(
                expected, viewModel.userDetailsState.value
            )

        }
    }

    @Test
    fun `test userDetailsState is notified with error message when calling getUserDetails`() {
        val username = "test"
        val failure = UserDomainEntities.Failure(SOCKET_TIME_OUT_EXCEPTION)
        val expected = UserDetailsState(
            errorMessage = failure.errorText
        )
        runBlocking {
            coEvery { getUserDetailsUseCase.invoke(username) } returns failure
            viewModel.getUserDetails(username)
            assertEquals(expected, viewModel.userDetailsState.value)
        }
    }
}