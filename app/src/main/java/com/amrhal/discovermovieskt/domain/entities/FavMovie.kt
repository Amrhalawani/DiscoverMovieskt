package com.amrhal.discovermovieskt.domain.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.amrhal.discovermovieskt.domain.core.Constants
import com.google.gson.annotations.SerializedName


@Entity(tableName = Constants.FAV_TABLE)
data class FavMovie(
@SerializedName("adult")
val adult: Boolean?,
@PrimaryKey
@SerializedName("id")
val id: Int?,
@SerializedName("popularity")
val popularity: Double?,

@SerializedName("poster_path")
val posterPath: String,

@SerializedName("release_date")
val releaseDate: String?,

@SerializedName("title")
val title: String?,
@SerializedName("vote_average")
val voteAverage: Double?
)

 {
     constructor() : this(
         false,
         0,
         0.0,
         "",
         "",
         "",
         0.0
     )
}