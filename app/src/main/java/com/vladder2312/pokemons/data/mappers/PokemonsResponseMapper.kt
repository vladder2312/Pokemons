package com.vladder2312.pokemons.data.mappers

import com.vladder2312.pokemons.data.responses.PokemonObj
import com.vladder2312.pokemons.data.responses.PokemonsResponse
import com.vladder2312.pokemons.domain.Pokemon
import com.vladder2312.pokemons.domain.Pokemons
import javax.inject.Inject

class PokemonsResponseMapper @Inject constructor() {

    fun transform(pokemonsResponse: PokemonsResponse): Pokemons {
        with(pokemonsResponse) {
            return Pokemons(
                count = count,
                list = results.map { transform(it) }
            )
        }
    }

    fun transform(pokemonObject: PokemonObj): Pokemon {
        with(pokemonObject) {
            return Pokemon(
                id = url.removeSuffix("/").substringAfterLast("/"),
                name = name
            )
        }
    }
}