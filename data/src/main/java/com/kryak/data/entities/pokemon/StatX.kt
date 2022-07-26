package com.kryak.data.entities.pokemon

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StatX(
    @Json(name = "name")
    val name: String,
)
