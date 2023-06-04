package com.codechallenge.nearshoretest.model.models.eventseriesstories

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventSeriesStoriesList(
    @SerializedName("results")
    val results : List<EventSeriesStories>
): Parcelable