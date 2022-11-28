package com.vladder2312.pokemons.data.mappers

import com.vladder2312.pokemons.data.responses.PokemonDetailsResponse
import com.vladder2312.pokemons.data.responses.PokemonObj
import com.vladder2312.pokemons.data.responses.SpritesObj
import com.vladder2312.pokemons.domain.Pokemon
import com.vladder2312.pokemons.domain.PokemonDetails
import com.vladder2312.pokemons.domain.Sprites

object PokemonResponseMapper {

    fun transform(pokemonObject: PokemonObj): Pokemon {
        with(pokemonObject) {
            return Pokemon(
                id = url.removeSuffix("/").substringAfterLast("/"),
                name = name
            )
        }
    }

    fun transform(pokemonDetails: PokemonDetailsResponse): PokemonDetails {
        with(pokemonDetails) {
            return PokemonDetails(
                id = id,
                name = name.replaceFirstChar { it.uppercaseChar() },
                height = height,
                weight = weight,
                baseExperience = baseExperience,
                sprites = transform(sprites)
            )
        }
    }

    private fun transform(pokemonSprites: SpritesObj): Sprites {
        with(pokemonSprites) {
            return Sprites(
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
}