package com.tfandkusu.kgs.data.remote

import com.tfandkusu.kgs.data.remote.schema.GithubSearchResponse
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy

/**
 * kotlinx-serializationの動作確認用メソッド
 *
 * @param jsonString JSON文字列
 * @return JSONパース結果
 */
@OptIn(ExperimentalSerializationApi::class)
fun decodeGithubResponse(jsonString: String): GithubSearchResponse {
    val format = Json {
        ignoreUnknownKeys = true
        namingStrategy = JsonNamingStrategy.SnakeCase
    }
    return format.decodeFromString(jsonString)
}
