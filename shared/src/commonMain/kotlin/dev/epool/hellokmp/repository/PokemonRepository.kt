package dev.epool.hellokmp.repository

import dev.epool.hellokmp.extensions.toPokemonIdentity
import dev.epool.hellokmp.network.PokemonApi
import dev.epool.hellokmp.network.models.PokemonResult

internal class PokemonRepository(
    private val pokemonApi: PokemonApi
) {

    suspend fun getPokemons(limit: Int? = null) = pokemonApi.getPokemons(limit).map(PokemonResult::toPokemonIdentity)

}