package com.codechallenge.nearshoretest.model.models.characters

import android.os.Parcelable
import com.codechallenge.nearshoretest.model.models.thumbnails.Thumbnail
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarvelChar(
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String,
    @SerializedName("description")
    val description : String,
    @SerializedName("thumbnail")
    val thumbnail : Thumbnail,
    @SerializedName("resourceURI")
    val resourceURI : String,
    ): Parcelable