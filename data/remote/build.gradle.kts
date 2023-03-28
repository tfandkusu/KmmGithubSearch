import com.tfandkusu.kgs.CommonPlugin

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.google.devtools.ksp").version("1.7.20-1.0.8")
    id("de.jensklingenberg.ktorfit") version "1.0.0"
}
apply<CommonPlugin>()

configure<de.jensklingenberg.ktorfit.gradle.KtorfitGradleConfiguration> {
    version = libs.versions.ktorfit.get()
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "remote"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies{
                implementation(
                    "de.jensklingenberg.ktorfit:ktorfit-lib:" + libs.versions.ktorfit.get()
                )
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.tfandkusu.kgs.data.remote"
    defaultConfig {
        minSdk = 23
    }
}

dependencies {
    add(
        "kspCommonMainMetadata",
        "de.jensklingenberg.ktorfit:ktorfit-ksp:" + libs.versions.ktorfit.get()
    )
}
