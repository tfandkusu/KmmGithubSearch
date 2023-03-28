package com.tfandkusu.kgs.data.remote

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform