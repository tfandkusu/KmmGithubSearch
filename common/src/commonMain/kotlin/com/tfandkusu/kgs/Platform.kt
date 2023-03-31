package com.tfandkusu.kgs

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform