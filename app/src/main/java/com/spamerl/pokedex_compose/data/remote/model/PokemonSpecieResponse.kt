package com.spamerl.pokedex_compose.data.remote.model


data class PokemonSpecieResponse(
    val color: Color,
    val id: Int,
)

data class Color(
    val name: String,
    val url: String
)