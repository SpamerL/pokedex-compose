package com.spamerl.pokedex_compose.domain.model

data class PokemonListModel(
    var id: Int,
    var name: String,
    var image: String,
    var color: String
)