package com.moviemania.kmm.android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviemania.kmm.MovieInfo
import com.moviemania.kmm.repo.MovieRepo
import com.moviemania.kmm.usecase.MoviesUsecase
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
