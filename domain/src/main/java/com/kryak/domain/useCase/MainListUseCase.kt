package com.kryak.domain.useCase

import androidx.paging.PagingData
import com.kryak.domain.model.PokemonListModel
import com.kryak.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainListUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    fun execute(): Flow<PagingData<PokemonListModel>> = repository.getPokemonList()
}
