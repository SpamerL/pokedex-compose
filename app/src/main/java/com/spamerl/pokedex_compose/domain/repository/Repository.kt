package com.spamerl.pokedex_compose.domain.repository

import androidx.paging.PagingData
import com.spamerl.pokedex_compose.data.remote.model.CombinedListItem
import com.spamerl.pokedex_compose.domain.model.PokemonModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getPokemonList(): Flow<PagingData<CombinedListItem>>
    fun getPokemonById(id: Int): Flow<PokemonModel>
}