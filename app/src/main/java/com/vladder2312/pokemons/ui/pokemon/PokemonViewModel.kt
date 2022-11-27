package com.vladder2312.pokemons.ui.pokemon

import androidx.lifecycle.ViewModel
import com.vladder2312.pokemons.data.responses.PokemonsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val repository: PokemonsRepository
) : ViewModel() {

}