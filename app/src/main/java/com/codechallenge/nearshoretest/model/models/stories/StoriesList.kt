package com.codechallenge.nearshoretest.model.models.stories

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class StoriesList(
    @SerializedName("available")
    val available : Int,
    @SerializedName("items")
    val items : List<Story>
): Parcelable