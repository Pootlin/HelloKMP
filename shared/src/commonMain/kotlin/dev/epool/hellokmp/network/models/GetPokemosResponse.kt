package dev.epool.hellokmp.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetPokemonResponse(
    @SerialName("count") val count: Int,
    @SerialName("results") val pokemonResults: List<PokemonResult>
)