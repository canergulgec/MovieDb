import dependencies.Dependencies
import extension.domainModuleLibraries
import extension.implement

plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Versions.App.compileSdkVersion

    defaultConfig {
        minSdk = Versions.App.minSdkVersion
        targetSdk = Versions.App.targetSdkVersion
        testInstrumentationRunner = Configs.androidInstrumentationRunner
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

kapt {
    generateStubs = true
}

dependencies {
    implementation(project(":data"))
    implementation(project(":core"))

    implement(domainModuleLibraries)
    kapt(Dependencies.Dagger.daggerHiltCompiler)
}
