package com.codechallenge.nearshoretest.model.models.events

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event(
    @SerializedName("resourceURI")
    val resourceURI : String,
    @SerializedName("name")
    val name : String
): Parcelable