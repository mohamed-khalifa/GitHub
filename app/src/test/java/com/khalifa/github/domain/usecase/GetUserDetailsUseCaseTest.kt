package com.khalifa.github.domain.usecase

import com.khalifa.github.domain.repository.UsersRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetUserDetailsUseCaseTest {

    private val userSearchRepository: UsersRepository = mockk(relaxed = true)
    private val getUserDetailsUseCase =
        GetUserDetailsUseCase(userSearchRepository)

    @Test
    fun `test repository getUsersDetails is called from GetUserDetailsUseCase`() {
        runBlocking {
            getUserDetailsUseCase.invoke("test")
            coVerify(exactly = 1) { userSearchRepository.getUsersDetails("test") }
        }
    }
}