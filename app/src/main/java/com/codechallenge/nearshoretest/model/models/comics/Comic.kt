package com.codechallenge.nearshoretest.model.models.comics

import android.os.Parcelable
import com.codechallenge.nearshoretest.model.models.thumbnails.Thumbnail
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comic(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title : String,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail
): Parcelable