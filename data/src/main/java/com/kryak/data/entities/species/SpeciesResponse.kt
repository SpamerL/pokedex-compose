package com.kryak.data.entities.species

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpeciesResponse(
    @Json(name = "color")
    val color: Color,
    @Json(name = "name")
    val name: String
)
