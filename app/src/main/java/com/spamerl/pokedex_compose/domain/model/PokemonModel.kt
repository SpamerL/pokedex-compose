package com.spamerl.pokedex_compose.domain.model

import com.spamerl.pokedex_compose.data.remote.model.PokemonResponse

data class PokemonModel(
    val id: Int,
    //val moves: List<PokemonResponse.Move>,
    val name: String,
    val baseExperience: Int,
    val species: PokemonResponse.Species,
    val sprite: String,
    //val types: List<PokemonResponse.Type>,
    val color: String? = null,
    val locationAreaEncountersURL: String? = null,
    val locationAreaEncounters: List<String>? = null,
    val height: Int,
    val weight: Int,
    //stats
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val speed: Int,
    val special_attack: Int,
    val special_defense: Int
)