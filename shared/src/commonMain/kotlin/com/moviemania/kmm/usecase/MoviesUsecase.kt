package com.moviemania.kmm.usecase

import com.moviemania.kmm.MovieResponse

expect class MoviesUsecase {
    suspend fun getPopularMovies(): MovieResponse
}
