package com.codechallenge.nearshoretest.model.models.series

import android.os.Parcelable
import com.codechallenge.nearshoretest.model.models.comics.Comic
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/*
"series": {
          "available": 3,
          "collectionURI": "http://gateway.marvel.com/v1/public/characters/1011334/series",
          "items": [
            {
              "resourceURI": "http://gateway.marvel.com/v1/public/series/1945",
              "name": "Avengers: The Initiative (2007 - 2010)"
            },
            ...
          ],
          "returned": 3
        },
 */

@Parcelize
data class SeriesList(
    @SerializedName("available")
    val available : Int,
    @SerializedName("items")
    val items : List<Serie>
): Parcelable