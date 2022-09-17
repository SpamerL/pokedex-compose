package com.kryak.pokedex_compose.core_network.api

import com.kryak.pokedex_compose.core_network.model.PokemonListResponse
import com.kryak.pokedex_compose.core_network.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("/api/v2/pokemon/")
    suspend fun getPokemonList(@Query("offset") offset: Int): PokemonListResponse

    @GET("/api/v2/pokemon-species/{name}")
    suspend fun getPokemonSpeciesByName(@Path("name") name: String)

    @GET("/api/v2/pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id: Int): PokemonResponse
}
