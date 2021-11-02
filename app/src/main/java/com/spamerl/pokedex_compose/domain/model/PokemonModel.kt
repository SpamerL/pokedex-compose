package com.spamerl.pokedex_compose.domain.model

data class PokemonModel(
    val name:String = "",
    val id: Int = 0,
    val image: String = "",
    val hp: Int = 0,
    val attack: Int = 0,
    val defense: Int = 0,
    val speed: Int = 0,
    val special_attack: Int = 0,
    val special_defense: Int = 0
)
