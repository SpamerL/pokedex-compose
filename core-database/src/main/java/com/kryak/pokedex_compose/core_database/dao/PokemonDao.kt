package com.kryak.pokedex_compose.core_database.dao

import androidx.room.* // ktlint-disable no-wildcard-imports
import com.kryak.pokedex_compose.core_database.model.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query(value = "SELECT * FROM PokemonEntity WHERE id = :pokemonId")
    suspend fun getPokemonEntityStream(pokemonId: Int): Flow<PokemonEntity>

    @Query(value = "SELECT * FROM PokemonEntity")
    suspend fun getPokemonsEntityStream(): Flow<List<PokemonEntity>>

    @Query(value = "SELECT * FROM PokemonEntity t WHERE t.favorite = 1")
    suspend fun getFavorite(): Flow<List<PokemonEntity>>

    @Query(value = "SELECT * FROM PokemonEntity WHERE name LIKE :searchQuery")
    suspend fun searchPokemonByName(searchQuery: String?): Flow<List<PokemonEntity>>

    @Update
    suspend fun updatePokemons(entities: List<PokemonEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemons(entities: List<PokemonEntity>)

    @Query(value = "DELETE FROM PokemonEntity WHERE id in (:ids)")
    suspend fun deletePokemons(ids: List<Int>)
}
