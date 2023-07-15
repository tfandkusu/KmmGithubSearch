import com.tfandkusu.kgs.CommonPlugin

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
}
apply<CommonPlugin>()

android {
    namespace = "com.tfandkusu.kgs"
    defaultConfig {
        applicationId = "com.tfandkusu.kgs.android"
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.8"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":feature:home"))
    implementation(project(":common"))
    implementation(libs.appcompat)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.compose.ui.ui)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.preview)
    implementation(libs.compose.fundation)
    implementation(libs.compose.material3)
    implementation(libs.material)
    implementation(libs.navigation.compose)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.napier)
    implementation(libs.kotlinx.datetime)
}
