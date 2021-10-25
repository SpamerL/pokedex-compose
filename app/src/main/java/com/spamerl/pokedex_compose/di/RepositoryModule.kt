package com.spamerl.pokedex_compose.di

import com.spamerl.pokedex_compose.data.remote.api.PokeApi
import com.spamerl.pokedex_compose.data.repository.RepositoryImpl
import com.spamerl.pokedex_compose.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesRepository(api: PokeApi): Repository = RepositoryImpl(api = api)
}