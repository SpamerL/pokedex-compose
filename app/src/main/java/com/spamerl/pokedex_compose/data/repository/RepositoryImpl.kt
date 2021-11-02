package com.spamerl.pokedex_compose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.spamerl.pokedex_compose.data.remote.api.PokeApi
import com.spamerl.pokedex_compose.data.remote.model.CombinedListItem
import com.spamerl.pokedex_compose.data.remote.model.toPokemonUIModel
import com.spamerl.pokedex_compose.data.remote.paging.PokemonPagedSource
import com.spamerl.pokedex_compose.domain.model.PokemonModel
import com.spamerl.pokedex_compose.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


open class RepositoryImpl @Inject constructor(
    private val api: PokeApi
): Repository {
    override fun getPokemonList(): Flow<PagingData<CombinedListItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 10
            ),
            pagingSourceFactory = { PokemonPagedSource(api) }
        ).flow
    }

    override fun getPokemonById(id: Int): Flow<PokemonModel> = flow { emit(api.getPokemonById(id)) }
        .map { it.toPokemonUIModel() }
}