import com.tfandkusu.kgs.CommonPlugin

plugins {
    id("com.android.application")
    kotlin("android")
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.1"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":data:remote"))
    implementation(project(":common"))
    implementation(libs.appcompat)
    implementation(libs.compose.ui.ui)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.preview)
    implementation(libs.compose.fundation)
    implementation(libs.compose.material)
    implementation(libs.navigation.fragment.ktx)
}
