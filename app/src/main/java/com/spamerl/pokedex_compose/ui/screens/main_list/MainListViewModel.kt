package com.spamerl.pokedex_compose.ui.screens.main_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kryak.domain.model.PokemonListModel
import com.kryak.domain.useCase.MainListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainListViewModel @Inject constructor(
    private val getPokemonUseCase: MainListUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<PagingData<PokemonListModel>> = MutableStateFlow(PagingData.empty())
    val state: StateFlow<PagingData<PokemonListModel>> get() = _state

    init {
        getPokemonList()
    }

    private fun getPokemonList() {
        getPokemonUseCase.execute()
            .onEach {
                _state.value = it
            }
            .flowOn(Dispatchers.IO)
            .catch { e -> e.printStackTrace() }
            .cachedIn(viewModelScope)
            .distinctUntilChanged()
            .launchIn(viewModelScope)
    }
}
