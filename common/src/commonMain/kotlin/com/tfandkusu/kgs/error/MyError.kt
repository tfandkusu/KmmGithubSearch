package com.tfandkusu.kgs.error

sealed class MyError : Throwable() {

    /**
     * サーバーエラーが発生
     *
     * @param statusCode ステータスコード
     */
    data class Server(val statusCode: Int) : MyError()

    /**
     * ネットワークエラーが発生
     */
    object Network : MyError()
}
