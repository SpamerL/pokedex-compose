package com.kryak.pokedex_compose.core_network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sprites(
    @Json(name = "front_default")
    val frontDefault: String
)
