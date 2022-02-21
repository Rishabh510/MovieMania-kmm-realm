package com.parismeow.kmm

import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*

val httpClient = HttpClient {
    install(JsonFeature) {
        serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Napier.v(message = message)
            }
        }
        level = LogLevel.ALL
        logger = Logger.DEFAULT
    }

    install(ResponseObserver) {
        onResponse { response ->
            Napier.d(message = "HTTP status: ${response.status.value}")
        }
    }

    install(DefaultRequest) {
        header(HttpHeaders.ContentType, ContentType.Application.Json)
    }
}
