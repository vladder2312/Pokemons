package com.vladder2312.pokemons.data.responses

import com.google.gson.annotations.SerializedName

data class PokemonDetailsResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("base_experience") val baseExperience: Int,
    @SerializedName("sprites") val sprites: SpritesObj
)