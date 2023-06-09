package com.codechallenge.nearshoretest.model.models.urls

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Url(
    @SerializedName("type")
    val type : String,
    @SerializedName("url")
    val url : String,
): Parcelable