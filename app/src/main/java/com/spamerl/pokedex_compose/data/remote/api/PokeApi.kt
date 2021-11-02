package com.spamerl.pokedex_compose.data.remote.api

import com.spamerl.pokedex_compose.data.remote.model.PokemonListResponse
import com.spamerl.pokedex_compose.data.remote.model.PokemonResponse
import com.spamerl.pokedex_compose.data.remote.model.PokemonSpecieResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("/api/v2/pokemon/")
    suspend fun getPokemonList(@Query("offset")offset: Int): Response<PokemonListResponse>

    @GET("/api/v2/pokemon-species/{name}")
    suspend fun getPokemonSpeciesByName(@Path("name")name: String): Response<PokemonSpecieResponse>

    @GET("/api/v2/pokemon/{id}")
    suspend fun getPokemonById(@Path("id")id: Int): PokemonResponse
}