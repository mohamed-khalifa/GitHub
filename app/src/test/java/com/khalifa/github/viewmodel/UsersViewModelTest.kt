package com.khalifa.github.viewmodel

import androidx.paging.PagingData
import com.khalifa.github.domain.entity.UserDomainEntities
import com.khalifa.github.domain.usecase.UserSearchUseCase
import com.khalifa.github.presentation.viewmodel.UsersViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class UsersViewModelTest : ViewModelTest() {
    private val userSearchUseCase: UserSearchUseCase = mockk(relaxed = true)
    private val viewModel = UsersViewModel(userSearchUseCase)

    @Test
    fun `test searchResultState is notified with search results when calling userSearch`() {
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
        val pagingDateFlow = flowOf(
            PagingData.from(
                listOf(
                    userDomainItem
                )
            )
        )
        runBlocking {
            coEvery { userSearchUseCase.invoke(username) } returns pagingDateFlow
            viewModel.userSearch(username)
            Assert.assertTrue(
                viewModel.searchResultState.value.usersList != null
            )
        }
    }
}