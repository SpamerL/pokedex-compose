package com.kryak.data.pagination

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.kryak.data.api.PokeApi
import com.kryak.data.db.PokeDB
import com.kryak.data.db.RemoteKeys
import com.kryak.data.entities.PokemonModel
import com.kryak.data.util.CombinedCall
import timber.log.Timber
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ListRemoteMediator @Inject constructor(
    private val api: PokeApi,
    private val db: PokeDB,
    private val combinedCall: CombinedCall
) : RemoteMediator<Int, PokemonModel>() {

    private val pokeDAO = db.pokeDAO()
    private val remoteDAO = db.remoteDAO()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonModel>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(20) ?: 0
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    prevPage
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    nextPage
                }
            }

            val response = api.getPagingPokemon(page)
            var endOfPaginationReached = false
            if (response.isSuccessful) {
                val data = response.body()
                endOfPaginationReached = data?.next?.isEmpty() == true
                val pokemonModel = combinedCall.combinedCall(data?.results!!)
                Timber.tag("combinedCall data -").v(pokemonModel.toString())
                data.let {
                    db.withTransaction {
                        if (loadType == LoadType.REFRESH) {
                            pokeDAO.deletePokemons()
                            remoteDAO.deletePokemonRemoteKeys()
                        }
                        val nextKey =
                            if (endOfPaginationReached) null else page + state.config.pageSize
                        val prevKey =
                            if (data.previous == null) null else page - state.config.pageSize

                        val keys = pokemonModel.map { pokemon ->
                            RemoteKeys(
                                pokemon.id,
                                prevKey,
                                nextKey
                            )
                        }
                        val dbChar = pokemonModel.map { pokemon ->
                            PokemonModel(
                                id = pokemon.id,
                                name = pokemon.name,
                                color = pokemon.color,
                                image = pokemon.image
                            )
                        }
                        remoteDAO.insertPokemonRemoteKeys(keys)
                        pokeDAO.insertPokemons(dbChar)
                    }
                }
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, PokemonModel>
    ): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                db.withTransaction { db.remoteDAO().getPokemonRemoteKeys(id) }
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, PokemonModel>
    ): RemoteKeys? {
        return state.firstItemOrNull()?.let { pokemon ->
            db.withTransaction { db.remoteDAO().getPokemonRemoteKeys(pokemon.id) }
        }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, PokemonModel>
    ): RemoteKeys? {
        return state.lastItemOrNull()?.let { pokemon ->
            db.withTransaction { db.remoteDAO().getPokemonRemoteKeys(pokemon.id) }
        }
    }
}
