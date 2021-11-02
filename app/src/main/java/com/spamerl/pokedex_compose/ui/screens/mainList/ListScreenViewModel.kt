package com.spamerl.pokedex_compose.ui.screens.mainList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.spamerl.pokedex_compose.data.remote.model.CombinedListItem
import com.spamerl.pokedex_compose.domain.usecase.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ListScreenViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase
): ViewModel() {

    private var _flowPokemonList: MutableStateFlow<PagingData<CombinedListItem>> =
        MutableStateFlow(PagingData.empty())
    val list: StateFlow<PagingData<CombinedListItem>> get() = _flowPokemonList

    init {
        getPokemons()
    }

    private fun getPokemons() {
        getPokemonListUseCase.execute()
            .onEach { _flowPokemonList.value = it }
            .flowOn(Dispatchers.IO)
            .catch { e -> e.printStackTrace() }
            .cachedIn(viewModelScope)
            .distinctUntilChanged()
            .launchIn(viewModelScope)
    }
}