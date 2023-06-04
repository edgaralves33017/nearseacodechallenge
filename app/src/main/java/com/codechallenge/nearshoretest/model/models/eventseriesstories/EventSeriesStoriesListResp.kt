package com.codechallenge.nearshoretest.model.models.eventseriesstories

import com.google.gson.annotations.SerializedName

data class EventSeriesStoriesListResp(
    @SerializedName("data")
    val data : EventSeriesStoriesList
)