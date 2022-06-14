package com.khalifa.github.domain.usecase

import com.khalifa.github.domain.repository.UsersRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class UserSearchUseCaseTest {

    private val userSearchRepository: UsersRepository = mockk(relaxed = true)
    private val userSearchUseCase = UserSearchUseCase(userSearchRepository)

    @Test
    fun `test repository getUsers is called from UserSearchUseCase`() {
        runBlocking {
            userSearchUseCase.invoke("test")
            coVerify(exactly = 1) { userSearchRepository.getUsers("test") }
        }
    }
}