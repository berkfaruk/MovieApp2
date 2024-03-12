package com.berkfaruk.app.movieapp2.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_search")
data class SearchModel(
    @PrimaryKey
    val Poster: String = "",
    val Title: String = "",
    val Type: String = "",
    val Year: String = "",
    val imdbID: String = ""
)
