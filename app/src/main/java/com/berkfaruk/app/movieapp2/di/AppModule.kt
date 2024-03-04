package com.berkfaruk.app.movieapp2.di

import com.berkfaruk.app.movieapp2.data.api.MovieApi
import com.berkfaruk.app.movieapp2.data.repository.MovieRepositoryImpl
import com.berkfaruk.app.movieapp2.domain.repository.MovieRepository
import com.berkfaruk.app.movieapp2.utils.Const.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    @Singleton
    fun provideMovieApp() : MovieApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }
    @Provides
    @Singleton
    fun provideMovieRepository(
        api : MovieApi
    ) : MovieRepository{
        return MovieRepositoryImpl(api)
    }
//Singleton ile kapsam Application
    //Provides?
}