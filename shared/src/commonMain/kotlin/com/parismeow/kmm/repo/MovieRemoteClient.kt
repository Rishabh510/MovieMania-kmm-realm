package com.parismeow.kmm.repo

import com.parismeow.kmm.MovieResponse
import com.parismeow.kmm.httpClient
import io.ktor.client.request.*

const val requestUrl =
    "https://api.themoviedb.org/3/movie/popular?api_key="

suspend fun getListPopularMovies(): MovieResponse {
    return httpClient.get(requestUrl)
}
