package com.kryak.data.repository

import androidx.paging.* // ktlint-disable no-wildcard-imports
import com.kryak.data.api.PokeApi
import com.kryak.data.db.PokeDB
import com.kryak.data.entities.PokemonModel
import com.kryak.data.mapper.Mappers
import com.kryak.data.pagination.ListRemoteMediator
import com.kryak.data.util.CombinedCall
import com.kryak.domain.model.PokemonDetail
import com.kryak.domain.model.PokemonListModel
import com.kryak.domain.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonRepImpl @Inject constructor(
    private val api: PokeApi,
    private val database: PokeDB,
    private val mappers: Mappers,
    private val combinedCall: CombinedCall
) : PokemonRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getPokemonList(): Flow<PagingData<PokemonListModel>> {
        val pagingSourceFactory = { database.pokeDAO().getAllPokemons() }
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 20,
                enablePlaceholders = true
            ),
            remoteMediator = ListRemoteMediator(
                api,
                database,
                combinedCall
            ),
            pagingSourceFactory = pagingSourceFactory,
        ).flow
            .map { value: PagingData<PokemonModel> ->
                value.map { mappers.mapDataListToDomain(it) }
            }
            .flowOn(Dispatchers.IO)
    }

    override fun getPokemonById(id: Int): Flow<PokemonDetail> {
        return flow { emit(api.getPokemonById(id)) }
            .map { mappers.mapPokemonResponseToDomain(it.body()!!) }
            .flowOn(Dispatchers.IO)
    }
}
