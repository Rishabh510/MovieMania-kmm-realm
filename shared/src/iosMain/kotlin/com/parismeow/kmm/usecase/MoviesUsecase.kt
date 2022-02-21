package com.parismeow.kmm.usecase

import com.parismeow.kmm.MovieResponse
import com.parismeow.kmm.repo.MovieRepo

actual class MoviesUsecase {
    actual suspend fun getPopularMovies(): MovieResponse {
        val result = MovieRepo().getPopularMovies()
        return result.copy(movies = result.movies.subList(0, 10))
    }
}
