package com.tfandkusu.kgs

class AndroidPlatform : Platform {
    override val name: String = System.getProperty("os.name")
}

actual fun getPlatform(): Platform = AndroidPlatform()
