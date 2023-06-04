package com.codechallenge.nearshoretest.model.models.stories

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Story(
    @SerializedName("resourceURI")
    val resourceURI : String,
    @SerializedName("name")
    val name : String,
    @SerializedName("type")
    val type : String
): Parcelable