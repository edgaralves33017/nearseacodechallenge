package com.codechallenge.nearshoretest.model

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.codechallenge.nearshoretest.model.models.characters.MarvelChar
import com.codechallenge.nearshoretest.model.network.NetworkRepository
import com.codechallenge.nearshoretest.model.network.paginationsources.CharacterPaginationSource
import kotlinx.coroutines.flow.Flow


/**
 * Repository class that houses instances to [NetworkRepository].
 * As per MVVM architecture, every access to [Repository] is transparent, and whoever accesses the methods
 * doesn't need to know if they are using [NetworkRepository] or another repository.
 */
class Repository(
    /**
     * Instance of NetworkRepository
     */
    private val networkService: NetworkRepository
) {
    companion object {
        private var instance : Repository? = null

        /**
         * Singleton
         */
        @Synchronized
        fun getInstance(): Repository {
            if (instance == null) instance = Repository(NetworkRepository.create())
            return instance!!
        }
    }
    /**
     * ApiService from NetworkingRepository
     */
    private val apiService
        get() = networkService.apiService

    val PAGE_LIMIT = 10

    fun getCharacters(name: String?) : Flow<PagingData<MarvelChar>> {
        return Pager(config = PagingConfig(pageSize = PAGE_LIMIT), pagingSourceFactory = { CharacterPaginationSource(apiService, name, PAGE_LIMIT) }).flow
    }

/*
    suspend fun getCharacters(page: Int, name: String?) = flow <ApiResult<List<MarvelChar>>>{
        val ts: Long = Date().time
        val stringToHash: String = "$ts${BuildConfig.MARVEL_API_PRIVATE_KEY}${BuildConfig.MARVEL_API_PUBLIC_KEY}"
        val hash: String = md5(stringToHash).toHex()
        emit(ApiResult.Loading(true))
        val response = apiService.getCharacters(BuildConfig.MARVEL_API_PUBLIC_KEY, hash, ts, PAGE_LIMIT, (PAGE_LIMIT * (page-1)), name)
        emit(ApiResult.Success(response.body()?.data?.results))
    }.catch { e ->
        emit(ApiResult.Failure(e.message ?: "Unknown Error"))
    }*/
}