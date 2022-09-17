package com.kryak.pokedex_compose.core_network.model


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Species(
    val name: String,
    val url: String
)