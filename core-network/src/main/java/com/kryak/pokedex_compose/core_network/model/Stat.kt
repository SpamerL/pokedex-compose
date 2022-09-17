package com.kryak.pokedex_compose.core_network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Stat(
    @Json(name = "base_stat")
    val baseStat: Int,
    val effort: Int,
    val stat: StatX
)
