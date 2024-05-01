package com.berkfaruk.app.movieapp2.data.repository

import com.berkfaruk.app.movieapp2.data.api.MovieApi
import com.berkfaruk.app.movieapp2.database.MoviesDao
import com.berkfaruk.app.movieapp2.domain.model.DetailModel
import com.berkfaruk.app.movieapp2.domain.model.SearchModel
import com.berkfaruk.app.movieapp2.domain.repository.MovieRepository
import com.berkfaruk.app.movieapp2.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api : MovieApi,
    private val dao : MoviesDao
) : MovieRepository  {
    override fun getMovieList(movieTitle: String): Flow<Resource<List<SearchModel>>> = flow{
        emit(Resource.Loading())
        try {
            val data = api.getMovieList(movieTitle).Search
            dao.deleteMovie()
            dao.addMovies(data)
            emit(Resource.Success(data))
        }catch (e : Exception){
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error", data = emptyList()))
        }
    }

    override fun getMovieDetail(imdbId: String): Flow<Resource<DetailModel>> = flow {
        emit(Resource.Loading())
        try {
            val movieDetail = api.getMovieDetail(imdbId = imdbId)
            emit(Resource.Success(movieDetail))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        }
    }

    override fun getMovieFromLocal(): Flow<List<SearchModel>> {
        return dao.getAllMovies()
    }


}