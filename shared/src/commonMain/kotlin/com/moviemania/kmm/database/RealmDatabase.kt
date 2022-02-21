package com.moviemania.kmm.database

import com.moviemania.kmm.MovieInfoRealm
import com.moviemania.kmm.MovieResponseRealm
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.query

class RealmDatabase {

    val realm: Realm by lazy {
        val configuration = RealmConfiguration.with(
            schema = setOf(
                MovieInfoRealm::class,
                MovieResponseRealm::class
            )
        )
        Realm.open(configuration)
    }

    fun getAllMovies(): List<MovieResponseRealm> {
        return realm.query<MovieResponseRealm>().find()
    }

    fun addMovies(movies: MovieResponseRealm) {
        realm.writeBlocking {
            copyToRealm(movies)
        }
    }

    fun clearAllMovies() {
        realm.writeBlocking {
            query<MovieResponseRealm>().find().delete()
        }
    }
}
