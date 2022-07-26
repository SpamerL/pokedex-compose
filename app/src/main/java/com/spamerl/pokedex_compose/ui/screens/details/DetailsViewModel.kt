package com.spamerl.pokedex_compose.ui.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kryak.domain.model.PokemonDetail
import com.kryak.domain.useCase.DetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPokemonUseCase: DetailUseCase,
    handle: SavedStateHandle
) : ViewModel() {

    private val _state: MutableStateFlow<PokemonDetail> = MutableStateFlow(PokemonDetail())
    val state: StateFlow<PokemonDetail> get() = _state

    init {
        val id = handle.get<Int>("id") ?: throw Exception()
        getPokemonByID(id)
    }

    private fun getPokemonByID(id: Int) {
        getPokemonUseCase.execute(id)
            .onEach {
                _state.value = it
            }
            .flowOn(Dispatchers.IO)
            .catch { e -> e.printStackTrace() }
            .distinctUntilChanged()
            .launchIn(viewModelScope)
    }
}
