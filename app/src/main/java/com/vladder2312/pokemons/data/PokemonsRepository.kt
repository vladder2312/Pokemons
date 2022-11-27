package com.vladder2312.pokemons.data

import com.vladder2312.pokemons.domain.Pokemons
import com.vladder2312.pokemons.domain.Resource

interface PokemonsRepository {

    suspend fun getPokemons(limit: Int, offset: Int): Resource<Pokemons>
}