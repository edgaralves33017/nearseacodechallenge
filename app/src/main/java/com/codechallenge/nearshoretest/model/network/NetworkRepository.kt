package com.codechallenge.nearshoretest.model.network

import com.codechallenge.nearshoretest.model.network.retrofit.RetrofitClient

/**
 * NetworkRepository houses the instance of the [RetrofitClient]
 */
class NetworkRepository {
    /**
     * [RetrofitClient] instance
     */
    val apiService = RetrofitClient.retroclient

    companion object {
        fun create(): NetworkRepository {
            return NetworkRepository()
        }
    }
}