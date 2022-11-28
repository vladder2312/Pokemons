package com.vladder2312.pokemons.data

import com.vladder2312.pokemons.data.responses.PokemonDetailsResponse
import com.vladder2312.pokemons.data.responses.PokemonsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonsService {

    @GET("pokemon")
    suspend fun getPokemons(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<PokemonsResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") id: String
    ): Response<PokemonDetailsResponse>
}