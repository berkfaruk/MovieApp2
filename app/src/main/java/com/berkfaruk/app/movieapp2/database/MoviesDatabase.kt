package com.berkfaruk.app.movieapp2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.berkfaruk.app.movieapp2.domain.model.DetailModel
import com.berkfaruk.app.movieapp2.domain.model.SearchModel

@Database(entities = [SearchModel::class, DetailModel::class], version = 1)
abstract class MoviesDatabase: RoomDatabase() {

    abstract val moviesDao: MoviesDao

}