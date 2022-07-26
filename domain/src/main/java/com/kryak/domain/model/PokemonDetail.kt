package com.kryak.domain.model

data class PokemonDetail(
    val name: String,
    val height: Int,
    val id: Int,
    val sprites: String,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val special_attack: Int,
    val special_defense: Int,
    val speed: Int,
    val weight: Int
) {
    constructor() : this(
        "",
        0,
        0,
        "",
        0,
        0,
        0,
        0,
        0,
        0,
        0
    )
}
