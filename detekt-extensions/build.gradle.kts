plugins {
    // バージョンの多重指定になるので、ここではバージョンを設定しない
    // このモジュールは JVM のモジュール
    id("org.jetbrains.kotlin.jvm")
}
dependencies {
    compileOnly(libs.detekt.api)
}
