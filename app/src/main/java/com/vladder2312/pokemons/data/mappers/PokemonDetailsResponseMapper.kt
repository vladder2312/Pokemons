package com.vladder2312.pokemons.data.mappers

import com.vladder2312.pokemons.data.responses.PokemonDetailsResponse
import com.vladder2312.pokemons.data.responses.SpritesObj
import com.vladder2312.pokemons.domain.PokemonDetails
import com.vladder2312.pokemons.domain.Sprites
import javax.inject.Inject

class PokemonDetailsResponseMapper @Inject constructor() {

    fun transform(pokemonDetails: PokemonDetailsResponse) = with(pokemonDetails) {
        PokemonDetails(
            id = id,
            name = name.replaceFirstChar { it.uppercaseChar() },
            height = height,
            weight = weight,
            baseExperience = baseExperience,
            sprites = transform(sprites)
        )
    }

    private fun transform(pokemonSprites: SpritesObj) = with(pokemonSprites) {
        Sprites(
            backDefault = backDefault,
            backFemale = backFemale,
            backShiny = backShiny,
            backShinyFemale = backShinyFemale,
            frontDefault = frontDefault,
            frontFemale = frontFemale,
            frontShiny = frontShiny,
            frontShinyFemale = frontShinyFemale
        )
    }
}