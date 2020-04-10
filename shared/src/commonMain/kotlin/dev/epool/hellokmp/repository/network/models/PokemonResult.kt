package dev.epool.hellokmp.repository.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResult(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String
)