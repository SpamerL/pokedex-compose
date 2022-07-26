package com.kryak.data.di

import com.kryak.data.api.PokeApi
import com.kryak.data.mapper.Mappers
import com.kryak.data.util.CombinedCall
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryProvModule {
    @Provides
    @Singleton
    fun provideMapper(): Mappers {
        return Mappers()
    }

    @Provides
    @Singleton
    fun provideCombiner(api: PokeApi, mappers: Mappers): CombinedCall {
        return CombinedCall(api, mappers)
    }
}
