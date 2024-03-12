package com.berkfaruk.app.movieapp2.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkfaruk.app.movieapp2.domain.model.DetailModel
import com.berkfaruk.app.movieapp2.domain.repository.MovieRepository
import com.berkfaruk.app.movieapp2.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor (
    private val repository: MovieRepository
) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String?>()
    val data = MutableLiveData<DetailModel?>()


    fun getMovieDetail(imdbId: String) {
        viewModelScope.launch {
            repository.getMovieDetail(imdbId).collect { result ->
                when(result) {
                    is Resource.Error ->{
                        isLoading.value = false
                        error.value = result.message
                    }
                    is Resource.Loading ->{
                        isLoading.value = true
                    }
                    is Resource.Success ->{
                        isLoading.value = false
                        data.value = result.data
                    }
                }
            }
        }
    }

}