package dev.epool.hellokmp.extensions

import dev.epool.hellokmp.models.PokemonIdentity
import dev.epool.hellokmp.network.models.PokemonResult

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