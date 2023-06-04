package com.codechallenge.nearshoretest.model.models.comics

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/*
"available": 12,
          "collectionURI": "http://gateway.marvel.com/v1/public/characters/1011334/comics",
          "items": [
            {
              "resourceURI": "http://gateway.marvel.com/v1/public/comics/21366",
              "name": "Avengers: The Initiative (2007) #14"
            },
            ...
          ],
 */

@Parcelize
data class ComicsList(
    @SerializedName("available")
    val available : Int,
    @SerializedName("items")
    val items : List<Comic>
): Parcelable