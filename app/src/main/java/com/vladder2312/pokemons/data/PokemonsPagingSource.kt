package com.vladder2312.pokemons.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vladder2312.pokemons.data.mappers.PokemonResponseMapper
import com.vladder2312.pokemons.domain.Pokemon
import retrofit2.HttpException
import javax.inject.Inject

class PokemonsPagingSource @Inject constructor(
    private val pokemonsService: PokemonsService
) : PagingSource<Int, Pokemon>() {

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val page: Int = params.key ?: 1
        val pageSize: Int = params.loadSize.coerceAtMost(ServiceConstants.POKEMONS_LIMIT)
        val offset = if (params.key != null) {
            ((page - 1) * ServiceConstants.POKEMONS_LIMIT) + 1
        } else {
            0
        }
        val response = pokemonsService.getPokemons(pageSize, offset)
        return if (response.isSuccessful) {
            val pokemons = checkNotNull(response.body()).let {
                it.results.map { obj -> PokemonResponseMapper.transform(obj) }
            }
            val nextKey = if (pokemons.size < ServiceConstants.POKEMONS_LIMIT) null else page + 1
            val prevKey = if (page == 1) null else page - 1
            LoadResult.Page(pokemons, prevKey, nextKey)
        } else {
            LoadResult.Error(HttpException(response))
        }
    }
}