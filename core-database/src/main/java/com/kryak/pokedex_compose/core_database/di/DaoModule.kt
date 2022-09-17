package com.kryak.pokedex_compose.core_database.di

import com.kryak.pokedex_compose.core_database.PokemonDatabase
import com.kryak.pokedex_compose.core_database.dao.PokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    @Singleton
    fun providesPokemonDao(database: PokemonDatabase): PokemonDao = database.pokemonDao()
}
