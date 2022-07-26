package com.kryak.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kryak.data.entities.PokemonModel

@Dao
interface PokeDAO {

    @Query("SELECT * FROM pokemon")
    fun getAllPokemons(): PagingSource<Int, PokemonModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemons(pokeList: List<PokemonModel>)

    @Query("DELETE FROM pokemon")
    suspend fun deletePokemons()
}
