package com.vladder2312.pokemons.ui.pokemon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladder2312.pokemons.data.PokemonsInteractor
import com.vladder2312.pokemons.domain.PokemonDetails
import com.vladder2312.pokemons.domain.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val pokemonsInteractor: PokemonsInteractor
) : ViewModel() {

    val pokemonDetails = MutableLiveData<Resource<PokemonDetails>>(Resource.loading(null))

    fun getPokemonDetails(id: String) {
        viewModelScope.launch {
            pokemonsInteractor.getPokemon(id).collect { pokemonDetails.value = it }
        }
    }
}