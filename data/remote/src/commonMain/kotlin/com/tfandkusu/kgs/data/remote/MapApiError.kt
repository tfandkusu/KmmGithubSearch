package com.tfandkusu.kgs.data.remote

import com.tfandkusu.kgs.error.NetworkException
import io.ktor.utils.io.errors.IOException

fun mapApiError(e: Throwable): Throwable {
    return if(e is IOException) {
        NetworkException
    }else {
        e
    }
}
