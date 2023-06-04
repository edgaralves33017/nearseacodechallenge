package com.codechallenge.nearshoretest.model.models.comics

import com.google.gson.annotations.SerializedName

data class ComicsListResp(
    @SerializedName("data")
    val data : ComicsList
)