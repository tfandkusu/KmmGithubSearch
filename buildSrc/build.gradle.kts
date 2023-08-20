plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(libs.android.gradle)
    implementation(libs.kotlin.gradle)
    implementation(libs.spotless.gradle)
    implementation(platform(libs.google.cloud.libraries.bom))
    implementation(libs.google.cloud.bigquery)
}
