package com.vladder2312.pokemons.data

import com.vladder2312.pokemons.data.mappers.PokemonDetailsResponseMapper
import com.vladder2312.pokemons.data.mappers.PokemonsResponseMapper
import com.vladder2312.pokemons.domain.Resource
import javax.inject.Inject

class PokemonsRepositoryImpl @Inject constructor(
    private val service: PokemonsService,
    private val pokemonDetailsMapper: PokemonDetailsResponseMapper,
    private val pokemonsResponseMapper: PokemonsResponseMapper
) : PokemonsRepository {

    override suspend fun getPokemons(limit: Int, offset: Int) =
        service.getPokemons(limit, offset).let {
            if (it.isSuccessful && it.body() != null) {
                Resource.success(pokemonsResponseMapper.transform(it.body()!!))
            } else {
                Resource.error(it.message(), null)
            }
        }

    override suspend fun getPokemonDetails(id: String) = service.getPokemon(id).let {
        if (it.isSuccessful && it.body() != null) {
            Resource.success(pokemonDetailsMapper.transform(it.body()!!))
        } else {
            Resource.error(it.message(), null)
        }
    }
}