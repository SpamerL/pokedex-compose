package com.kryak.pokedex_compose.core_database.di

import android.content.Context
import androidx.room.Room
import com.kryak.pokedex_compose.core_database.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesPokemonDatabase(
        @ApplicationContext context: Context
    ): PokemonDatabase = Room.databaseBuilder(
        context,
        PokemonDatabase::class.java,
        "Pokemon-database"
    ).build()
}
