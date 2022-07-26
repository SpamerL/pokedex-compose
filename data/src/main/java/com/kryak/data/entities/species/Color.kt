package com.kryak.data.entities.species

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Color(
    @Json(name = "name")
    val name: String
)
