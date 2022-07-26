package com.kryak.data.di

import android.content.Context
import androidx.room.Room
import com.kryak.data.db.PokeDAO
import com.kryak.data.db.PokeDB
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
    fun provideDao(database: PokeDB): PokeDAO {
        return database.pokeDAO()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): PokeDB {
        return Room.databaseBuilder(
            appContext,
            PokeDB::class.java,
            "PokeDB"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
