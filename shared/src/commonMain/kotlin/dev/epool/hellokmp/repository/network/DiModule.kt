package dev.epool.hellokmp.repository.network

import dev.epool.hellokmp.httpClientEngine
import io.ktor.client.engine.HttpClientEngine
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

internal val networkModule = Kodein.Module("networkModule") {
    bind<GithubClient>() with singleton { GithubClient(instance()) }
    bind<PokemonApi>() with singleton { PokemonApi(instance()) }
    bind<HttpClientEngine>() with singleton { httpClientEngine }
}