package com.codechallenge.nearshoretest.model.models.thumbnails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Thumbnail(
    @SerializedName("path")
    val path : String,
    @SerializedName("extension")
    val extension : String
): Parcelable