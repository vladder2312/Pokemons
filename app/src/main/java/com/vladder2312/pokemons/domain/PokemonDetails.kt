package com.vladder2312.pokemons.domain

data class PokemonDetails(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val baseExperience: Int,
    val sprites: Sprites
)