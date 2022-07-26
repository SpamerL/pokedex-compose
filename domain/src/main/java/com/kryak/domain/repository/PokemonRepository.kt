package com.kryak.domain.repository

import androidx.paging.PagingData
import com.kryak.domain.model.PokemonDetail
import com.kryak.domain.model.PokemonListModel
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(): Flow<PagingData<PokemonListModel>>

    fun getPokemonById(id: Int): Flow<PokemonDetail>
}
