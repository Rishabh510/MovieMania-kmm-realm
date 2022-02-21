package com.moviemania.kmm.repo

import com.moviemania.kmm.MovieResponse
import com.moviemania.kmm.httpClient
import io.ktor.client.request.*

const val requestUrl =
    "https://api.themoviedb.org/3/movie/popular?api_key="

suspend fun getListPopularMovies(): MovieResponse {
    return httpClient.get(requestUrl)
}
