package com.parismeow.kmm.android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parismeow.kmm.MovieInfo
import com.parismeow.kmm.repo.MovieRepo
import com.parismeow.kmm.usecase.MoviesUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
    private val moviesUsecase: MoviesUsecase = MoviesUsecase()
    private val movieRepo: MovieRepo = MovieRepo()
    private val _movies = MutableLiveData<List<MovieInfo>>()
    val movies: LiveData<List<MovieInfo>> = _movies

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = moviesUsecase.getPopularMovies();
            withContext(Dispatchers.Main) {
                _movies.value = result.movies
            }
        }
    }

    fun removeAll() {
        movieRepo.clearAll()
    }
}
