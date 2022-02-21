package com.parismeow.kmm.usecase

import com.parismeow.kmm.MovieResponse
import com.parismeow.kmm.repo.MovieRepo

actual class MoviesUsecase {
    actual suspend fun getPopularMovies(): MovieResponse {
        return MovieRepo().getPopularMovies()
    }
}
