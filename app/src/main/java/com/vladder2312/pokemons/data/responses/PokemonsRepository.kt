package com.vladder2312.pokemons.data.responses

import androidx.paging.PagingData
import com.vladder2312.pokemons.domain.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonsRepository {
    suspend fun getPokemons(): Flow<PagingData<Pokemon>>
}