package com.spamerl.pokedex_compose.data.remote.api

import com.spamerl.pokedex_compose.data.remote.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {

    companion object {
      const val BASE_URL = "https://pokeapi.co/api/v2/"
    }

    @GET("/api/v2/pokemon/?offset={offset}")
    suspend fun getPokemonsList(@Path("offset") offset: Int): Response<PagedResponse<PokemonListItemResponse>>

    @GET("/api/v2/pokemon/{id}/")
    suspend fun getPokemonByID(@Path("id") id: Int) : PokemonResponse

    @GET("/api/v2/pokemon-species/{name}/")
    suspend fun getPokemonSpecieById(@Path("name") name: String): PokemonSpecieResponse
}