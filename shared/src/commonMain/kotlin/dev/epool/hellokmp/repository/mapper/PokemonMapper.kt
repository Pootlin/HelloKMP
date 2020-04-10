package dev.epool.hellokmp.repository.mapper

import dev.epool.hellokmp.domain.models.PokemonIdentity
import dev.epool.hellokmp.repository.network.models.PokemonResult

private val POKEMON_ID_REGEX = """https://pokeapi.co/api/v2/pokemon/(\d+)/""".toRegex()

internal fun PokemonResult.toPokemonIdentity(): PokemonIdentity {
    val pokemonId = POKEMON_ID_REGEX.find(url)?.let {
        val (id) = it.destructured
        id.toIntOrNull()
    } ?: 0
    return PokemonIdentity(
        pokemonId,
        name,
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$pokemonId.png"
    )
}