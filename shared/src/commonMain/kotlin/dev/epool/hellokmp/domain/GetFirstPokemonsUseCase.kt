package dev.epool.hellokmp.domain

import dev.epool.hellokmp.repository.PokemonRepository

internal class GetFirstPokemonsUseCase(
    private val pokemonRepository: PokemonRepository
) {

    suspend operator fun invoke() = pokemonRepository.getPokemons(150)

}