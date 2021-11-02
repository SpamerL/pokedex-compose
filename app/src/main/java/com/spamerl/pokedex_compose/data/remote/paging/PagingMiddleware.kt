package com.spamerl.pokedex_compose.data.remote.paging

import com.spamerl.pokedex_compose.data.remote.api.PokeApi
import com.spamerl.pokedex_compose.data.remote.model.CombinedList
import com.spamerl.pokedex_compose.data.remote.model.CombinedListItem
import com.spamerl.pokedex_compose.data.remote.model.PokemonSpecieResponse
import com.spamerl.pokedex_compose.util.PokeColor
import timber.log.Timber

class PagingMiddleware {

    suspend fun comb(api: PokeApi, Offset: Int): CombinedList {
        val baseList = api.getPokemonList(offset = Offset).body()!!

        val speciesList: MutableList<PokemonSpecieResponse> = mutableListOf()

        for (i in baseList.results) {
            val specieResponse = api.getPokemonSpeciesByName(i.name).body()!!
            speciesList.add(specieResponse)
        }

        val combinedResults: MutableList<CombinedListItem> = mutableListOf()
        for (v in baseList.results) {

            val index = baseList.results.indexOf(v)

            val item: CombinedListItem? = PokeColor.ColorList[speciesList[index].color.name]?.let {
                CombinedListItem(
                    id = speciesList[index].id,
                    name = v.name,
                    image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + speciesList[index].id + ".png",
                    color = it
                )
            }

            combinedResults.add(item!!)
        }

        val info = CombinedList(
            count = baseList.count,
            next = baseList.next,
            previous = baseList.previous,
            combinedResults = combinedResults
        )
        Timber.d("middleware load info: $info")
        return info
    }
}