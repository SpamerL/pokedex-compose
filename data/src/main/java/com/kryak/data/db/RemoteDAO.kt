package com.kryak.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RemoteDAO {

    @Query("SELECT * FROM pokemon_remote_keys WHERE id = :pokemonId")
    suspend fun getPokemonRemoteKeys(pokemonId: Int): RemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonRemoteKeys(pokemonRemoteKeys: List<RemoteKeys>)

    @Query("DELETE FROM pokemon_remote_keys")
    suspend fun deletePokemonRemoteKeys()
}