package com.vladder2312.pokemons.data.mappers

import com.vladder2312.pokemons.data.ServiceConstants
import com.vladder2312.pokemons.data.responses.PokemonObj
import com.vladder2312.pokemons.data.responses.PokemonsResponse
import com.vladder2312.pokemons.domain.Pokemon
import com.vladder2312.pokemons.domain.Pokemons

object PokemonResponseMapper {

    fun transform(pokemonsResponse: PokemonsResponse) = Pokemons(
        count = pokemonsResponse.count,
        list = pokemonsResponse.results.map { transform(it) }
    )

    fun transform(pokemonObject: PokemonObj) = Pokemon(
        id = pokemonObject.url.removePrefix(ServiceConstants.BASE_URL).removeSuffix("/"),
        name = pokemonObject.name
    )
}