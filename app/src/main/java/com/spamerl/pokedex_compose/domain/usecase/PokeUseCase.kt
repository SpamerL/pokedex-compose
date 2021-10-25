package com.spamerl.pokedex_compose.domain.usecase

import androidx.paging.PagingData
import com.spamerl.pokedex_compose.domain.model.PokemonListModel
import com.spamerl.pokedex_compose.domain.model.PokemonModel
import com.spamerl.pokedex_compose.domain.model.PokemonSpecieModel
import com.spamerl.pokedex_compose.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetPokemonListUseCase(private val repo: Repository) {
    fun execute(): Flow<PagingData<PokemonListModel>> = repo.getPokemonList()
}

class GetPokemonByIdUseCase(private val repo: Repository) {
    fun execute(id: Int): Flow<PokemonModel> = repo.getPokemonById(id)
}

class GetPokemonSpecieByNameUseCase(private val repo: Repository) {
    fun execute(name: String): Flow<PokemonSpecieModel> = repo.getPokemonSpecieByName(name = name)
}