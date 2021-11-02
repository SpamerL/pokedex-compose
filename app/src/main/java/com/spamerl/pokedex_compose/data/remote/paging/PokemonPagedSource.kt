package com.spamerl.pokedex_compose.data.remote.paging

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.spamerl.pokedex_compose.data.remote.api.PokeApi
import com.spamerl.pokedex_compose.data.remote.model.CombinedListItem
import timber.log.Timber

class PokemonPagedSource(private val api: PokeApi): PagingSource<Int,CombinedListItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CombinedListItem> {
        val offset = params.key ?: 0
        return try {
            val response = PagingMiddleware().comb(api, offset)
            Timber.d("load: $response")
            var nextOffset: Int? = null

            if (response.next != null) {
                val uri = Uri.parse(response.next)
                val query = uri.getQueryParameter("offset")
                nextOffset = query?.toInt()
            }
            LoadResult.Page(
                data = response.combinedResults,
                prevKey = if (response.previous != "") null else offset - 20,
                nextKey = nextOffset
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CombinedListItem>): Int? {
        TODO("Not yet implemented")
    }
}