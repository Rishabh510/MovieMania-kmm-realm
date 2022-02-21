package com.parismeow.kmm

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.realmListOf
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    @SerialName("page")
    val page: Int? = null,
    @SerialName("results")
    val movies: List<MovieInfo> = emptyList(),
)

@Serializable
data class MovieInfo(
    @SerialName("id")
    val id: String? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("poster_path")
    val imageUrl: String? = null,
    @SerialName("vote_average")
    val rating: Float? = null,
)

fun MovieResponse.toRealmMovieResponse(): MovieResponseRealm {
    return MovieResponseRealm().apply {
        page = this@toRealmMovieResponse.page
        movies = realmListOf<MovieInfoRealm>().apply {
            addAll(this@toRealmMovieResponse.movies)
        }
    }
}

fun addAll(movies: List<MovieInfo>) {
    for (movie in movies){
        movie.toRealmMovieInfo()
    }
}

fun MovieInfo.toRealmMovieInfo(): MovieInfoRealm {
    return MovieInfoRealm().apply {
        id = this@toRealmMovieInfo.id
        title = this@toRealmMovieInfo.title
        imageUrl = this@toRealmMovieInfo.imageUrl
        rating = this@toRealmMovieInfo.rating
    }
}

class MovieResponseRealm : RealmObject {
    var page: Int? = null
    var movies: RealmList<MovieInfoRealm> = realmListOf()
}

class MovieInfoRealm : RealmObject {
    var id: String? = null
    var title: String? = null
    var imageUrl: String? = null
    var rating: Float? = null
}
