package com.kryak.pokedex_compose.core_network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Result>
)
