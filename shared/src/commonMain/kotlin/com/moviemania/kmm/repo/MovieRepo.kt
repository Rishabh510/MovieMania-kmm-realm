package com.moviemania.kmm.repo

import com.moviemania.kmm.MovieResponse
import com.moviemania.kmm.database.RealmDatabase
import com.moviemania.kmm.toRealmMovieResponse

class MovieRepo {

    private val database = RealmDatabase()

    suspend fun getPopularMovies(): MovieResponse {
        val result = getListPopularMovies()
        database.addMovies(result.toRealmMovieResponse())
        return result
    }

    fun clearAll() {
        database.clearAllMovies()
    }
}
