package com.berkfaruk.app.movieapp2.ui.home


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkfaruk.app.movieapp2.domain.model.SearchModel
import com.berkfaruk.app.movieapp2.domain.repository.MovieRepository
import com.berkfaruk.app.movieapp2.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository : MovieRepository
)  : ViewModel(){
     val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String?>()
    val data = MutableLiveData<List<SearchModel>?>()
    val roomData = MutableLiveData<List<SearchModel>?>()


    fun getMovieList(movieName : String){

        viewModelScope.launch {
            repository.getMovieList(movieName).collect {  result ->

                when(result){
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

    fun getMovieListFromDatabase() {

        viewModelScope.launch {
            val list = repository.getMovieFromLocal().collect{

                roomData.value = it

            }
        }

    }



}