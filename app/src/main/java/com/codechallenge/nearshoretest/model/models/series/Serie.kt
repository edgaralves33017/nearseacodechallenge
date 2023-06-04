package com.codechallenge.nearshoretest.model.models.series

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Serie(
    @SerializedName("resourceURI")
    val resourceURI : String,
    @SerializedName("name")
    val name : String
): Parcelable