package com.berkfaruk.app.movieapp2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.berkfaruk.app.movieapp2.domain.model.SearchModel

@Database(entities = [SearchModel::class], version = 1)
abstract class MoviesDatabase: RoomDatabase() {

    abstract val moviesDao: MoviesDao

}