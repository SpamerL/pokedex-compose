package com.kryak.domain.useCase

import com.kryak.domain.model.PokemonDetail
import com.kryak.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    fun execute(id: Int): Flow<PokemonDetail> = repository.getPokemonById(id)
}
