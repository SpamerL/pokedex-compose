package com.spamerl.pokedex_compose.data.remote.model


data class PokemonListResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Result>
)