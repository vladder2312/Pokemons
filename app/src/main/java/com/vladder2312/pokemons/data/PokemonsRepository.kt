package com.vladder2312.pokemons.data

import com.vladder2312.pokemons.domain.PokemonDetails
import com.vladder2312.pokemons.domain.Pokemons
import com.vladder2312.pokemons.domain.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonsRepository {
    suspend fun getPokemons(limit: Int, offset: Int): Resource<Pokemons>

    suspend fun getPokemonDetails(id: String): Resource<PokemonDetails>
}