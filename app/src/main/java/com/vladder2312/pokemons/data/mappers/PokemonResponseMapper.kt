package com.vladder2312.pokemons.data.mappers

import com.vladder2312.pokemons.data.responses.PokemonDetailsResponse
import com.vladder2312.pokemons.data.responses.PokemonObj
import com.vladder2312.pokemons.data.responses.SpritesObj
import com.vladder2312.pokemons.domain.Pokemon
import com.vladder2312.pokemons.domain.PokemonDetails
import com.vladder2312.pokemons.domain.Sprites

object PokemonResponseMapper {

    fun transform(pokemonObject: PokemonObj) = Pokemon(
        id = pokemonObject.url
            .removeSuffix("/")
            .substringAfterLast("/"),
        name = pokemonObject.name
    )

    fun transform(pokemonDetails: PokemonDetailsResponse) = PokemonDetails(
        id = pokemonDetails.id,
        name = pokemonDetails.name.replaceFirstChar { it.uppercaseChar() },
        height = pokemonDetails.height,
        weight = pokemonDetails.weight,
        baseExperience = pokemonDetails.baseExperience,
        sprites = transform(pokemonDetails.sprites)
    )

    private fun transform(pokemonSprites: SpritesObj) = Sprites(
        backDefault = pokemonSprites.backDefault,
        backFemale = pokemonSprites.backFemale,
        backShiny = pokemonSprites.backShiny,
        backShinyFemale = pokemonSprites.backShinyFemale,
        frontDefault = pokemonSprites.frontDefault,
        frontFemale = pokemonSprites.frontFemale,
        frontShiny = pokemonSprites.frontShiny,
        frontShinyFemale = pokemonSprites.frontShinyFemale
    )
}