package com.berkfaruk.app.movieapp2.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.berkfaruk.app.movieapp2.domain.model.SearchModel
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies_search")
    fun getAllMovies() : Flow<List<SearchModel>>
    //movienin ismine göre sorgu gelsin

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovies(movie: List<SearchModel>)

    @Query("DELETE FROM movies_search")
    fun deleteMovie()

    //DTO araştır

}