package dev.epool.hellokmp.network

import dev.epool.hellokmp.network.models.GetPokemonResponse
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
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

internal class PokemonApi(
    private val httpClientEngine: HttpClientEngine
) {

    private val client by lazy {
        HttpClient(httpClientEngine) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(Json(JsonConfiguration.Stable.copy(ignoreUnknownKeys = true)))
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }

    suspend fun getPokemons(limit: Int? = null) = client.get<GetPokemonResponse> {
        url {
            takeFrom("https://pokeapi.co/")
            encodedPath = "api/v2/pokemon" + limit?.let { "?limit=$it" }.orEmpty()
        }
    }.pokemonResults

}