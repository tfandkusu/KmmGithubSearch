import com.tfandkusu.kgs.CommonPlugin

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.google.devtools.ksp").version("1.8.0-1.0.9")
    kotlin("plugin.serialization") version "1.8.0"
}
apply<CommonPlugin>()

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":common"))
                implementation(libs.coroutine)
                implementation(libs.ktor.client.core)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.napier)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotest.assertions)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.client.okhttp)
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
    }
}

android {
    namespace = "com.tfandkusu.kgs.data.remote"
}
