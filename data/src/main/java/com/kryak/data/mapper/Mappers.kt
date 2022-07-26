package com.kryak.data.mapper

import com.kryak.data.entities.PokemonModel
import com.kryak.data.entities.Result
import com.kryak.data.entities.pokemon.PokemonResponse
import com.kryak.data.entities.species.SpeciesResponse
import com.kryak.domain.model.PokemonDetail
import com.kryak.domain.model.PokemonListModel

class Mappers {

    fun mapToPokemonModel(result: Result, speciesResponse: SpeciesResponse, pokemonResponse: PokemonResponse): PokemonModel {
        return PokemonModel(
            id = pokemonResponse.id,
            name = result.name,
            color = speciesResponse.color.name,
            image = pokemonResponse.sprites.frontDefault
        )
    }

    fun mapDataListToDomain(data: PokemonModel): PokemonListModel {
        return PokemonListModel(
            id = data.id,
            name = data.name,
            image = data.image,
            color = data.color
        )
    }

    fun mapPokemonResponseToDomain(pokemonResponse: PokemonResponse): PokemonDetail {
        return PokemonDetail(
            name = pokemonResponse.name,
            height = pokemonResponse.height,
            id = pokemonResponse.id,
            sprites = pokemonResponse.sprites.frontDefault,
            hp = pokemonResponse.stats[0].baseStat,
            attack = pokemonResponse.stats[1].baseStat,
            defense = pokemonResponse.stats[2].baseStat,
            special_attack = pokemonResponse.stats[3].baseStat,
            special_defense = pokemonResponse.stats[4].baseStat,
            speed = pokemonResponse.stats[5].baseStat,
            weight = pokemonResponse.weight
        )
    }
}
