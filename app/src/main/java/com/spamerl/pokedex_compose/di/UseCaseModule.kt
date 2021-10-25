package com.spamerl.pokedex_compose.di

import com.spamerl.pokedex_compose.domain.repository.Repository
import com.spamerl.pokedex_compose.domain.usecase.GetPokemonByIdUseCase
import com.spamerl.pokedex_compose.domain.usecase.GetPokemonListUseCase
import com.spamerl.pokedex_compose.domain.usecase.GetPokemonSpecieByNameUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetPokemonListUseCase(repo: Repository) = GetPokemonListUseCase(repo)

    @Provides
    @ViewModelScoped
    fun provideGetPokemonByIdUseCase(repo: Repository) = GetPokemonByIdUseCase(repo)

    @Provides
    @ViewModelScoped
    fun provideGetPokemonSpecieByName(repo: Repository) = GetPokemonSpecieByNameUseCase(repo)
}