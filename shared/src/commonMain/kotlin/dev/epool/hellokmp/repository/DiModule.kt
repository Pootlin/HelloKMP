package dev.epool.hellokmp.repository

import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

internal val repositoryModule = Kodein.Module("repositoryModule") {
    bind<GithubRepository>() with singleton { GithubRepository(instance()) }
    bind<PokemonRepository>() with singleton { PokemonRepository(instance()) }
}