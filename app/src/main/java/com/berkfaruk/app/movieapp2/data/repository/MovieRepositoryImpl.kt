package com.berkfaruk.app.movieapp2.data.repository

import com.berkfaruk.app.movieapp2.data.api.MovieApi
import com.berkfaruk.app.movieapp2.domain.model.SearchModel
import com.berkfaruk.app.movieapp2.domain.repository.MovieRepository
import com.berkfaruk.app.movieapp2.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api : MovieApi
) : MovieRepository  {
    override fun getMovieList(movieTitle: String): Flow<Resource<List<SearchModel>>> = flow {
        emit(Resource.Loading())
        try {
            val data = api.getMovieList(movieTitle).Search
            emit(Resource.Success(data))
        }catch (e : Exception){
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        }
    }


}