package com.codechallenge.nearshoretest.model.network.paginationsources


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.codechallenge.nearshoretest.BuildConfig
import com.codechallenge.nearshoretest.model.models.characters.MarvelChar
import com.codechallenge.nearshoretest.model.network.api.ApiService
import com.codechallenge.nearshoretest.md5
import com.codechallenge.nearshoretest.model.models.comics.Comic
import com.codechallenge.nearshoretest.toHex
import java.util.Date

class ComicPaginationSource(private val apiService: ApiService, private val id: Int, private val PAGE_LIMIT: Int) :
    PagingSource<Int, Comic>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Comic> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val ts: Long = Date().time
            val stringToHash: String =
                "$ts${BuildConfig.MARVEL_API_PRIVATE_KEY}${BuildConfig.MARVEL_API_PUBLIC_KEY}"
            val hash: String = md5(stringToHash).toHex()
            val response = apiService.getComics(
                id,
                BuildConfig.MARVEL_API_PUBLIC_KEY,
                hash,
                ts,
                PAGE_LIMIT,
                (page - 1) * PAGE_LIMIT,
            )
            val res = response.body()?.data?.results ?: mutableListOf()
            LoadResult.Page(
                data = res,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = null
                //nextKey = if (res.isEmpty()) null else page.plus(1)
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Comic>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}
