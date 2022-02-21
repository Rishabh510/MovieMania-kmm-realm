package com.parismeow.kmm.repo

import com.parismeow.kmm.MovieResponse
import com.parismeow.kmm.database.RealmDatabase
import com.parismeow.kmm.toRealmMovieResponse

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
