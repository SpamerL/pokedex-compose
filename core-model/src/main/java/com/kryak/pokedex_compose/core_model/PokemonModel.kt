package com.kryak.pokedex_compose.core_model

data class PokemonModel(
    val name: String = "",
    val id: Int = 0,
    val image: String,
    val hp: Int = 0,
    val attack: Int = 0,
    val defense: Int = 0,
    val speed: Int = 0,
    val special_attack: Int = 0,
    val special_defense: Int = 0,
    val weight: Int,
    val height: Int,
    val favorite: Boolean = false
)

