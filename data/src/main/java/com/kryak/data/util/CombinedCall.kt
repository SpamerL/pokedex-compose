package com.kryak.data.util

import com.kryak.data.api.PokeApi
import com.kryak.data.entities.PokemonModel
import com.kryak.data.entities.Result
import com.kryak.data.entities.pokemon.PokemonResponse
import com.kryak.data.entities.species.SpeciesResponse
import com.kryak.data.mapper.Mappers
import kotlinx.coroutines.* // ktlint-disable no-wildcard-imports
import timber.log.Timber
import javax.inject.Inject

class CombinedCall @Inject constructor(
    private val api: PokeApi,
    private val mappers: Mappers
) {
    suspend fun combinedCall(pokeList: List<Result>): List<PokemonModel> {
        val info: MutableList<PokemonModel> = mutableListOf()

        for (x in pokeList) {
            val speciesResponse: SpeciesResponse? = api.getSpeciesByName(x.name).body()
            val pokemonResponse: PokemonResponse? = api.getPokemonByName(x.name).body()
            val res = mappers.mapToPokemonModel(x, speciesResponse!!, pokemonResponse!!)
            info.add(res)
        }
        Timber.tag("combinedCall inside data -").v(info.toString())
        return info
    }
}

/*
pokeList.forEach { data ->
            val speciesResponse: SpeciesResponse? = api.getSpeciesByName(data.name).body()
            val pokemonResponse: PokemonResponse? = api.getPokemonByName(data.name).body()
            info.plus(Mappers().mapToPokemonModel(data, speciesResponse!!, pokemonResponse!!))
        }
 */
