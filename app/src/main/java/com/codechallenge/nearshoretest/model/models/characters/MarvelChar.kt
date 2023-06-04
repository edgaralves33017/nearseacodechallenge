package com.codechallenge.nearshoretest.model.models.characters

import android.os.Parcelable
import com.codechallenge.nearshoretest.model.models.comics.ComicsList
import com.codechallenge.nearshoretest.model.models.events.EventsList
import com.codechallenge.nearshoretest.model.models.series.SeriesList
import com.codechallenge.nearshoretest.model.models.stories.StoriesList
import com.codechallenge.nearshoretest.model.models.thumbnails.Thumbnail
import com.codechallenge.nearshoretest.model.models.urls.Url
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
    @SerializedName("comics")
    val comics : ComicsList,
    @SerializedName("series")
    val series : SeriesList,
    @SerializedName("stories")
    val stories : StoriesList,
    @SerializedName("events")
    val events : EventsList,
    @SerializedName("urls")
    val urls : List<Url>,
    ): Parcelable