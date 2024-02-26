package com.berkfaruk.app.movieapp2.data.api

import android.graphics.Movie
import com.berkfaruk.app.movieapp2.domain.model.MovieModel
import com.berkfaruk.app.movieapp2.utils.Const.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/")
    suspend fun getMovieList(
        @Query("s") movieTitle : String,
        @Query("apikey") apiKey : String = API_KEY
    ) : MovieModel
}