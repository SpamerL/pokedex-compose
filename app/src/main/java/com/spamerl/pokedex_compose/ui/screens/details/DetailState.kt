package com.spamerl.pokedex_compose.ui.screens.details

import com.kryak.domain.model.PokemonDetail

sealed class DetailState {
    object Loading : DetailState()
    data class DetailResult(val data: PokemonDetail) : DetailState()
    object Error : DetailState()
}
