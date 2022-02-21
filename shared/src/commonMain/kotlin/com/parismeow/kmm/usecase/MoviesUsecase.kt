package com.parismeow.kmm.usecase

import com.parismeow.kmm.MovieResponse

expect class MoviesUsecase {
    suspend fun getPopularMovies(): MovieResponse
}
