package com.kryak.pokedex_compose.core_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kryak.pokedex_compose.core_database.dao.PokemonDao
import com.kryak.pokedex_compose.core_database.model.PokemonEntity

@Database(
    entities = [PokemonEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
