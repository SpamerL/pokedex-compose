package com.spamerl.pokedex_compose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.spamerl.pokedex_compose.data.remote.api.PokeApi
import com.spamerl.pokedex_compose.data.remote.model.toPokemonModel
import com.spamerl.pokedex_compose.data.remote.model.toPokemonSpecieModel
import com.spamerl.pokedex_compose.data.remote.paging.PokemonListPagingDataSource
import com.spamerl.pokedex_compose.domain.model.PokemonListModel
import com.spamerl.pokedex_compose.domain.model.PokemonModel
import com.spamerl.pokedex_compose.domain.model.PokemonSpecieModel
import com.spamerl.pokedex_compose.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class RepositoryImpl(private val api: PokeApi): Repository {
    override fun getPokemonList(): Flow<PagingData<PokemonListModel>> {
        return Pager(
            config = PagingConfig(
                enablePlaceholders = false,
                pageSize = 20
            ),
            pagingSourceFactory = { PokemonListPagingDataSource(api) }
        ).flow
    }

    override fun getPokemonById(id: Int): Flow<PokemonModel> =
        flow { emit(api.getPokemonByID(id)) }
            .map { it.toPokemonModel() }

    override fun getPokemonSpecieByName(name: String): Flow<PokemonSpecieModel> =
        flow { emit(api.getPokemonSpecieById(name)) }
            .map { it.toPokemonSpecieModel() }
}