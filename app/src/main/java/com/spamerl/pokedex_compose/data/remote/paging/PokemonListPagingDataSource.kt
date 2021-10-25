package com.spamerl.pokedex_compose.data.remote.paging

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.spamerl.pokedex_compose.data.remote.api.PokeApi
import com.spamerl.pokedex_compose.data.remote.model.PokemonSpecieResponse
import com.spamerl.pokedex_compose.domain.model.PokemonListModel
import com.spamerl.pokedex_compose.util.PokemonListCombine

class PokemonListPagingDataSource(private val api: PokeApi): PagingSource<Int, PokemonListModel>() {
    private val initialOffset: Int = 0
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonListModel> {
        val offset: Int = params.key ?: initialOffset
        return try {
            val response = api.getPokemonsList(offset)
            val pagedResponse = response.body()
            val data = pagedResponse?.results

            //get speciesList data
            val speciesList = mutableListOf<PokemonSpecieResponse>()
            if (data != null) {
                for (i in data) {
                 val speciesResponse = api.getPokemonSpecieById(i.name)
                 speciesList.add(speciesResponse)
                }
            }

            val combined = PokemonListCombine(
                responseList = data,
                pokemonSpecieResponseList = speciesList
            ).combine()

            var nextOffset: Int? = null
            if (pagedResponse?.next != null) {
                val uri = Uri.parse(pagedResponse.next)
                val nextPagedOff = uri.getQueryParameter("offset")
                nextOffset = nextPagedOff?.toInt()
            }

            LoadResult.Page(
                data = combined,
                prevKey = if (offset == initialOffset) null else offset - 20,
                nextKey = nextOffset
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonListModel>): Int? = null
}