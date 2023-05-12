package com.example.deezerapimusicapp.viewmodel

import android.app.Application
import com.example.deezerapimusicapp.model.genre.GenreViewState
import com.example.deezerapimusicapp.repository.genre.GenreRepository
import com.example.deezerapimusicapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MusicCategoriesViewModel @Inject constructor(
    private val repository : GenreRepository,
    application: Application
) : BaseViewModel(application){

    private val _genreState = MutableStateFlow(GenreViewState())

    val genreState : StateFlow<GenreViewState> = _genreState.asStateFlow()

    suspend fun getGenre(){
        repository.getGenre().collect{result->
            when(result){
                is Resource.Success ->{

                    _genreState.value = result.data?.let {
                        GenreViewState(isSuccess = true,isLoading = false, genreList = it,"")
                    }!!

                }
                is Resource.Loading ->{
                    _genreState.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is Resource.Error -> {
                    _genreState.update {
                        it.copy(
                            error = "Error"
                        )
                    }
                }
            }
        }
    }


}