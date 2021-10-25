package com.spamerl.pokedex_compose.data.remote.model


import com.google.gson.annotations.SerializedName
import com.spamerl.pokedex_compose.domain.model.PokemonSpecieModel

data class PokemonSpecieResponse(
    @SerializedName("base_happiness")
    val baseHappiness: Int,
    @SerializedName("capture_rate")
    val captureRate: Int,
    val id: Int,
    val color: Color,
)

data class Color(
    val name: String,
    val url: String
)

fun PokemonSpecieResponse.toPokemonSpecieModel() = PokemonSpecieModel(
    id = id,
    color = color.name
)