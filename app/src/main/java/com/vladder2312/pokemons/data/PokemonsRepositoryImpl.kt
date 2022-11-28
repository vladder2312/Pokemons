package com.vladder2312.pokemons.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vladder2312.pokemons.data.mappers.PokemonResponseMapper
import com.vladder2312.pokemons.domain.Pokemon
import com.vladder2312.pokemons.domain.PokemonDetails
import com.vladder2312.pokemons.domain.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

    override suspend fun getPokemon(id: String): Flow<Resource<PokemonDetails>> {
        return flow {
            service.getPokemon(id).let {
                if (it.isSuccessful && it.body() != null) {
                    emit(Resource.success(PokemonResponseMapper.transform(it.body()!!)))
                } else {
                    emit(Resource.error(it.message(), null))
                }
            }
        }
    }
}