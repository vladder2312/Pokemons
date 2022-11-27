package com.vladder2312.pokemons.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladder2312.pokemons.data.PokemonsRepository
import com.vladder2312.pokemons.domain.Pokemons
import com.vladder2312.pokemons.domain.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pokemonsRepository: PokemonsRepository
) : ViewModel() {

    private val _pokemonList = MutableLiveData<Resource<Pokemons>>()
    val pokemonList: LiveData<Resource<Pokemons>> get() = _pokemonList

    init {
        getPokemonList()
    }

    fun getPokemonList() {
        viewModelScope.launch {
            _pokemonList.postValue(Resource.loading(null))
            pokemonsRepository.getPokemons(
                limit = POKEMON_LIMIT,
                offset = 0 //TODO
            ).let {
                _pokemonList.postValue(it)
            }
        }
    }

    private companion object {
        const val POKEMON_LIMIT = 50
    }
}