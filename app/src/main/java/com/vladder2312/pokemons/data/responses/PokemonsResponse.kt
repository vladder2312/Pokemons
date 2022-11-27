package com.vladder2312.pokemons.data.responses

import com.google.gson.annotations.SerializedName
import com.vladder2312.pokemons.data.mappers.PokemonResponseMapper
import com.vladder2312.pokemons.domain.Pokemons

data class PokemonsResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: String,
    @SerializedName("results") val results: List<PokemonObj>
)