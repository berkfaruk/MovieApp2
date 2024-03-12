package com.berkfaruk.app.movieapp2.di

import android.app.Application
import androidx.room.Room
import com.berkfaruk.app.movieapp2.database.MoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideRoom(context: Application) : MoviesDatabase {
        return Room.databaseBuilder(
            context,
            MoviesDatabase::class.java,
            "movieDb")
            .allowMainThreadQueries() //araştır
            .build()
    }

}