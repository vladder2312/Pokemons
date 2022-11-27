package com.vladder2312.pokemons.data.responses

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vladder2312.pokemons.data.PokemonsPagingSource
import com.vladder2312.pokemons.data.PokemonsService
import com.vladder2312.pokemons.data.ServiceConstants
import com.vladder2312.pokemons.domain.Pokemon
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonsRepositoryImpl @Inject constructor(
    private val service: PokemonsService
) : PokemonsRepository {

    override suspend fun getPokemons(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(
                pageSize = ServiceConstants.POKEMONS_LIMIT,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PokemonsPagingSource(service)
            }
        ).flow
    }
}