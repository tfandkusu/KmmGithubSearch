package com.tfandkusu.kgs

actual fun getPlatform(): Platform {
    return object : Platform {
        override val name: String
            get() = "PC"
    }
}
