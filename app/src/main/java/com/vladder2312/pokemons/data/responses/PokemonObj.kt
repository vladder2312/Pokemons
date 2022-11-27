package com.vladder2312.pokemons.data.responses

import com.google.gson.annotations.SerializedName
import com.vladder2312.pokemons.data.ServiceConstants
import com.vladder2312.pokemons.domain.Pokemon

data class PokemonObj(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)
