package com.kryak.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kryak.data.entities.PokemonModel

@Database(
    entities = [PokemonModel::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class PokeDB : RoomDatabase() {
    abstract fun pokeDAO(): PokeDAO
    abstract fun remoteDAO(): RemoteDAO
}
