import com.tfandkusu.kgs.CommonPlugin

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.google.devtools.ksp").version("1.8.22-1.0.11")
    kotlin("plugin.serialization") version "1.8.22"
    id ("org.jetbrains.kotlinx.kover")
}
apply<CommonPlugin>()

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":common"))
                implementation(libs.ktor.client.core)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.kotlinx.json)
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
        val jvmMain by getting {
            dependencies {
                implementation(libs.ktor.client.okhttp)
            }
        }
    }
}

android {
    namespace = "com.tfandkusu.kgs.data.remote"
}
