package com.kryak.domain

import com.kryak.domain.repository.PokemonRepository
import com.kryak.domain.useCase.DetailUseCase
import com.kryak.domain.useCase.MainListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    @ViewModelScoped
    fun provideMainListUseCase(repository: PokemonRepository) = MainListUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideDetailUseCase(repository: PokemonRepository) = DetailUseCase(repository)
}
