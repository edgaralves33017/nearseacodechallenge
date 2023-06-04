package com.codechallenge.nearshoretest.model.network.api


import com.codechallenge.nearshoretest.model.models.characters.MarvelCharListResp
import com.codechallenge.nearshoretest.model.models.comics.ComicsListResp
import com.codechallenge.nearshoretest.model.models.eventseriesstories.EventSeriesStoriesListResp
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
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
        @Query("nameStartsWith") nameSearch: String?,
    ): Response<MarvelCharListResp>

    @GET("characters/{id}/comics")
    suspend fun getComics(
        @Path("id") id: Int,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: Long,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): Response<ComicsListResp>

    @GET("characters/{id}/series")
    suspend fun getSeries(
        @Path("id") id: Int,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: Long,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): Response<EventSeriesStoriesListResp>

    @GET("characters/{id}/events")
    suspend fun getEvents(
        @Path("id") id: Int,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: Long,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): Response<EventSeriesStoriesListResp>

    @GET("characters/{id}/stories")
    suspend fun getStories(
        @Path("id") id: Int,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: Long,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): Response<EventSeriesStoriesListResp>
}