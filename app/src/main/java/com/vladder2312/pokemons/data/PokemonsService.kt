package com.vladder2312.pokemons.data

import com.vladder2312.pokemons.data.ServiceConstants.BASE_URL
import com.vladder2312.pokemons.data.responses.PokemonsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonsService {

    @GET(BASE_URL)
    suspend fun getPokemons(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<PokemonsResponse>
}