package com.vladder2312.pokemons.ui.pokemon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladder2312.pokemons.data.responses.PokemonsRepository
import com.vladder2312.pokemons.domain.PokemonDetails
import com.vladder2312.pokemons.domain.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val repository: PokemonsRepository
) : ViewModel() {

    val pokemonDetails = MutableLiveData<Resource<PokemonDetails>>()

    fun getDetails(id: String) {
        viewModelScope.launch {
            repository.getPokemon(id).collect { pokemonDetails.value = it }
        }
    }
}