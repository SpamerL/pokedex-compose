package com.kryak.pokedex_compose.core_network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonResponse(
    val height: Int,
    val id: Int,
    val name: String,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val weight: Int
)
