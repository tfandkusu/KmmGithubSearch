package com.tfandkusu.kgs.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp

val myHttpClient = HttpClient(OkHttp) {
}
