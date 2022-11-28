package com.vladder2312.pokemons.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.vladder2312.pokemons.data.PokemonsInteractor
import com.vladder2312.pokemons.domain.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pokemonsInteractor: PokemonsInteractor
) : ViewModel() {

    private val _pokemons = MutableStateFlow<PagingData<Pokemon>>(PagingData.empty())
    val pokemons = _pokemons.asStateFlow()

    private val _openScreenEvent = Channel<String>()
    val openScreenEvent = _openScreenEvent.receiveAsFlow()

    init {
        getPokemons()
    }

    private fun getPokemons() {
        pokemonsInteractor.getPokemons()
            .cachedIn(viewModelScope)
            .map { _pokemons.value = it }
            .launchIn(viewModelScope)
    }

    fun openPokemonScreen(id: String) {
        _openScreenEvent.trySend(id)
    }
}