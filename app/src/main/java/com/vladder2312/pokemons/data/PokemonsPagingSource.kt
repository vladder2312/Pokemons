package com.vladder2312.pokemons.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vladder2312.pokemons.domain.Pokemon
import com.vladder2312.pokemons.domain.Status
import javax.inject.Inject

class PokemonsPagingSource @Inject constructor(
    private val pokemonsRepository: PokemonsRepository
) : PagingSource<Int, Pokemon>() {

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val page: Int = params.key ?: 1
        val pageSize: Int = params.loadSize
        val offset = if (params.key != null) {
            ((page - 1) * pageSize) + 1
        } else {
            0
        }
        val response = pokemonsRepository.getPokemons(pageSize, offset)
        return if (response.status == Status.SUCCESS && response.data != null) {
            val pokemons = response.data.list
            val nextKey = if (pokemons.size < pageSize) null else page + 1
            val prevKey = if (page == 1) null else page - 1
            LoadResult.Page(pokemons, prevKey, nextKey)
        } else {
            LoadResult.Error(Exception(response.message))
        }
    }
}
