package com.codechallenge.nearshoretest.model.models.characters

import com.google.gson.annotations.SerializedName

/**
 * Model for the Entry object.
 */
data class MarvelCharList(
    @SerializedName("offset")
    val offset : Int,
    @SerializedName("limit")
    val limit : Int,
    @SerializedName("total")
    val total : Int,
    @SerializedName("count")
    val count : Int,
    @SerializedName("results")
    val results : List<MarvelChar>,
)