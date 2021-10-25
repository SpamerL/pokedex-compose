package com.spamerl.pokedex_compose.domain.repository

import androidx.paging.PagingData
import com.spamerl.pokedex_compose.domain.model.PokemonListModel
import com.spamerl.pokedex_compose.domain.model.PokemonModel
import com.spamerl.pokedex_compose.domain.model.PokemonSpecieModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getPokemonList(): Flow<PagingData<PokemonListModel>>

    fun getPokemonById(id: Int): Flow<PokemonModel>

    fun getPokemonSpecieByName(name: String): Flow<PokemonSpecieModel>
}