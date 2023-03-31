@file:Suppress("UnusedImport")

import com.tfandkusu.kgs.CommonPlugin

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}
apply<CommonPlugin>()

android {
    namespace = "com.tfandkusu.kgs.common"
}
