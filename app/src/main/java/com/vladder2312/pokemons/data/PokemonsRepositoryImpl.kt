package com.vladder2312.pokemons.data

import com.vladder2312.pokemons.data.mappers.PokemonResponseMapper
import com.vladder2312.pokemons.domain.Pokemons
import com.vladder2312.pokemons.domain.Resource
import javax.inject.Inject

class PokemonsRepositoryImpl @Inject constructor(
    private val service: PokemonsService
) : PokemonsRepository {

    override suspend fun getPokemons(limit: Int, offset: Int): Resource<Pokemons> {
        return service.getPokemons(limit, offset).let {
            when {
                it.isSuccessful -> Resource.success(
                    it.body()?.let { body -> PokemonResponseMapper.transform(body) }
                )
                else -> Resource.error(it.errorBody().toString(), null)
            }
        }
    }
}