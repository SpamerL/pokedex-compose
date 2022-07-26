package com.kryak.data.api

import com.kryak.data.entities.PagedResponse
import com.kryak.data.entities.pokemon.PokemonResponse
import com.kryak.data.entities.species.SpeciesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon/")
    suspend fun getPagingPokemon(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = 20
    ): Response<PagedResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemonById(
        @Path("id") id: Int
    ): Response<PokemonResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(
        @Path("name") name: String
    ): Response<PokemonResponse>

    @GET("pokemon-species/{name}")
    suspend fun getSpeciesByName(
        @Path("name") name: String
    ): Response<SpeciesResponse>
}
