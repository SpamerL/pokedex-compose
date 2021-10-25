package com.spamerl.pokedex_compose.util

import com.spamerl.pokedex_compose.data.remote.model.PokemonListItemResponse
import com.spamerl.pokedex_compose.data.remote.model.PokemonSpecieResponse
import com.spamerl.pokedex_compose.domain.model.PokemonListModel

class PokemonListCombine(private val responseList: List<PokemonListItemResponse>?, private val pokemonSpecieResponseList: List<PokemonSpecieResponse>) {
    fun combine(): MutableList<PokemonListModel> {
        val item = PokemonListModel(id = 0, name = "", image = "", color = "")
        val list: MutableList<PokemonListModel> = mutableListOf()
        if (responseList != null) {
            for (i in responseList) {
                val index = responseList.indexOf(i)
                item.name = i.name
                item.id = pokemonSpecieResponseList[index].id
                item.color = pokemonSpecieResponseList[index].color.name
                item.image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pokemonSpecieResponseList[index].id + ".png"
                list.add(item)
            }
        }
        return list
    }
}