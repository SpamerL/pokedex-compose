package com.spamerl.pokedex_compose.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spamerl.pokedex_compose.domain.model.PokemonModel
import com.spamerl.pokedex_compose.domain.usecase.GetPokemonByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    handle: SavedStateHandle,
    private val getPokemonInfo: GetPokemonByIdUseCase
): ViewModel() {

    private val _pokemon = MutableStateFlow(PokemonModel())
    val pokemon: StateFlow<PokemonModel> get() = _pokemon.asStateFlow()

    init {
        val id = handle.get<Int>("id") ?: throw Exception()
        getPokemon(id)
    }

    private fun getPokemon(id: Int) {
        getPokemonInfo.execute(id)
            .onEach { _pokemon.value = it }
            .flowOn(Dispatchers.IO)
            .catch { e -> e.printStackTrace() }
            .launchIn(viewModelScope)
    }
}