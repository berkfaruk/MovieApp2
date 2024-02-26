package com.berkfaruk.app.movieapp2.domain.model

data class MovieModel(
    val Response: String,
    val Search: ArrayList<SearchModel>,
    val totalResults: String
)
