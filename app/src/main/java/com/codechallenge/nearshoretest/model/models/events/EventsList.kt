package com.codechallenge.nearshoretest.model.models.events

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventsList(
    @SerializedName("available")
    val available : Int,
    @SerializedName("items")
    val items : List<Event>
): Parcelable