package com.kryak.data.di

import com.kryak.data.repository.PokemonRepImpl
import com.kryak.domain.repository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    internal abstract fun bindPokemonsRepository(repository: PokemonRepImpl): PokemonRepository
}
