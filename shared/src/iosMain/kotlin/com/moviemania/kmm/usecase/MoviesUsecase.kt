package com.moviemania.kmm.usecase

import com.moviemania.kmm.MovieResponse
import com.moviemania.kmm.repo.MovieRepo

actual class MoviesUsecase {
    actual suspend fun getPopularMovies(): MovieResponse {
        val result = MovieRepo().getPopularMovies()
        return result.copy(movies = result.movies.subList(0, 10))
    }
}
