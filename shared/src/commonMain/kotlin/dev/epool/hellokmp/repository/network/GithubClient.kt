package dev.epool.hellokmp.repository.network

import dev.epool.hellokmp.repository.network.models.GithubUser
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.get
import io.ktor.http.takeFrom
import kotlinx.coroutines.delay

internal class GithubClient(
    private val httpClientEngine: HttpClientEngine
) {

    private val client by lazy {
        HttpClient(httpClientEngine) {
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }

    suspend fun getUser(username: String) = client.get<GithubUser> {
        delay(1000)
        if (listOf(true, false).random()) {
            throw Exception(":'(")
        }
        url {
            takeFrom("https://api.github.com/")
            encodedPath = "users/$username"
        }
    }

}