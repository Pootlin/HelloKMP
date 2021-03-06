package dev.epool.hellokmp.repository

import dev.epool.hellokmp.repository.network.GithubClient

internal class GithubRepository(
    private val githubClient: GithubClient
) {

    suspend fun getUser(username: String) = githubClient.getUser(username)

}