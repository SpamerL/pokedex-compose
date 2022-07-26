package com.kryak.data.entities.pokemon

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sprites(
    @Json(name = "front_default")
    val frontDefault: String
)
