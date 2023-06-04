package com.codechallenge.nearshoretest.model.models.characters

import com.google.gson.annotations.SerializedName

/**
 * Model for the Entry object.
 */
data class MarvelCharListResp(
    @SerializedName("data")
    val data : MarvelCharList
)