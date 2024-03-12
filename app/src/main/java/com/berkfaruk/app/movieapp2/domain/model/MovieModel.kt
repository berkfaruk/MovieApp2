package com.berkfaruk.app.movieapp2.domain.model

data class MovieModel(
    val Response: String,
    val Search: List<SearchModel>,
    val totalResults: String
)
