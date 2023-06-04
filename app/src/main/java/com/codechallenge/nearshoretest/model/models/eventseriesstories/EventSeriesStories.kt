package com.codechallenge.nearshoretest.model.models.eventseriesstories

import android.os.Parcelable
import com.codechallenge.nearshoretest.model.models.thumbnails.Thumbnail
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventSeriesStories(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title : String,
    @SerializedName("description")
    val description : String,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail
): Parcelable