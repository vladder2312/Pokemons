package com.vladder2312.pokemons.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vladder2312.pokemons.domain.Pokemon
import com.vladder2312.pokemons.domain.PokemonDetails
import com.vladder2312.pokemons.domain.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonsInteractor @Inject constructor(
    private val repository: PokemonsRepository
) {

    fun getPokemons(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(
                pageSize = POKEMONS_LIMIT,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PokemonsPagingSource(repository)
            }
        ).flow
    }

    fun getPokemon(id: String): Flow<Resource<PokemonDetails>> {
        return flow {
            emit(repository.getPokemonDetails(id))
        }
    }

    private companion object {
        const val POKEMONS_LIMIT = 50
    }
}