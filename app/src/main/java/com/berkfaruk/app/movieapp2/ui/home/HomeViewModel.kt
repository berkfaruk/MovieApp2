package com.berkfaruk.app.movieapp2.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkfaruk.app.movieapp2.domain.model.SearchModel
import com.berkfaruk.app.movieapp2.domain.repository.MovieRepository
import com.berkfaruk.app.movieapp2.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository : MovieRepository
)  : ViewModel(){
     val _isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    val data = MutableLiveData<List<SearchModel>>()


    fun getMovieList(movieName : String){

        viewModelScope.launch {
            repository.getMovieList(movieName).collect {  result ->

                when(result){
                    is Resource.Error ->{
                        Log.d("DataTest12", "getMovieList: ${result.message}")
                    }
                    is Resource.Loading ->{
                        Log.d("DataTest12", "loading: ")
                    }
                    is Resource.Success ->{
                        Log.d("DataTest12", "getMovieList: ${result.data}")
                    }
                }
            }
        }
    }
}