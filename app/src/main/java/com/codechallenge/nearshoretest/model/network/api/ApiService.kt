package com.codechallenge.nearshoretest.model.network.api


import com.codechallenge.nearshoretest.model.models.characters.MarvelCharListResp
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface that has the necessary endpoint for us to make HTTP/HTTPS calls.
 */
interface ApiService {
    @GET("characters")
    suspend fun getCharacters(
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: Long,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("name") nameSearch: String?,
    ): Response<MarvelCharListResp>
}