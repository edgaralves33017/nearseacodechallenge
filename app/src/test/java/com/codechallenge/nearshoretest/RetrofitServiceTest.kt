package com.codechallenge.nearshoretest

import com.codechallenge.nearshoretest.model.network.api.ApiService
import com.google.gson.Gson
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitServiceTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var apiService: ApiService
    lateinit var gson: Gson

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        gson = Gson()
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ApiService::class.java)
    }


    @Test
    fun `get character list api test`() {
        runBlocking {
            val mockResponse = MockResponse()
            val body = loadFileText(this, "/marvel_char_list.json")
            mockWebServer.enqueue(mockResponse.setBody(body))
            val response = apiService.getCharacters(BuildConfig.MARVEL_API_PUBLIC_KEY, "", 0, 0, 0, null)
            val request = mockWebServer.takeRequest()
            assertEquals(false, response.body()?.data?.results?.isEmpty())
        }
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    private inline fun <reified T> loadFileText(
        caller: T,
        filePath: String
    ): String =
        T::class.java.getResource(filePath)?.readText() ?: throw IllegalArgumentException(
            "Could not find file $filePath. Make sure to put it in the correct resources folder for $caller's runtime."
        )

}