package com.tfandkusu.kgs.error

/**
 * サーバーエラーが発生
 *
 * @param statusCode ステータスコード
 */
data class ServerException(val statusCode: Int) : Exception()
