package com.tfandkusu.kgs.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy

expect fun getMyHttpClient(): HttpClient

@OptIn(ExperimentalSerializationApi::class)
fun ContentNegotiation.Config.myConfigure(){
    json(
        Json {
            ignoreUnknownKeys = true
            namingStrategy = JsonNamingStrategy.SnakeCase
        }
    )
}
