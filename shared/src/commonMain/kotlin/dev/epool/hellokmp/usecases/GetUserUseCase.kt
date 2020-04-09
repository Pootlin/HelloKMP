package dev.epool.hellokmp.usecases

import dev.epool.hellokmp.repository.GithubRepository

internal class GetUserUseCase(
    private val githubRepository: GithubRepository
) {

    suspend operator fun invoke(username: String) = githubRepository.getUser(username)

}