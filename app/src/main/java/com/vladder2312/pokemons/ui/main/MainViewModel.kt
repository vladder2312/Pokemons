package com.vladder2312.pokemons.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.vladder2312.pokemons.data.responses.PokemonsRepository
import com.vladder2312.pokemons.domain.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: PokemonsRepository
) : ViewModel() {

    val pokemons = MutableLiveData<PagingData<Pokemon>>()

    init {
        viewModelScope.launch {
            getPokemons()
        }
    }

    private suspend fun getPokemons() {
        repository.getPokemons()
            .cachedIn(viewModelScope)
            .map { pokemons.value = it }
            .launchIn(viewModelScope)
    }
}