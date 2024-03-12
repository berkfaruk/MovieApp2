package com.berkfaruk.app.movieapp2.domain.repository

import com.berkfaruk.app.movieapp2.domain.model.DetailModel
import com.berkfaruk.app.movieapp2.domain.model.SearchModel
import com.berkfaruk.app.movieapp2.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovieList(movieTitle:String) : Flow<Resource<List<SearchModel>>>

    fun getMovieDetail(imdbId : String) : Flow<Resource<DetailModel>>

    fun getMovieFromLocal() : Flow<List<SearchModel>>

}