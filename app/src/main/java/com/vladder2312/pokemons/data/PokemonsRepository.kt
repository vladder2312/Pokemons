package com.vladder2312.pokemons.data

import androidx.paging.PagingData
import com.vladder2312.pokemons.domain.Pokemon
import com.vladder2312.pokemons.domain.PokemonDetails
import com.vladder2312.pokemons.domain.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonsRepository {
    suspend fun getPokemons(): Flow<PagingData<Pokemon>>

    suspend fun getPokemon(id: String): Flow<Resource<PokemonDetails>>
}