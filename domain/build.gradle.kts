import dependencies.Dependencies
import dependencies.ModuleDependencies
import extension.implement

plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Configs.compileSdkVersion

    defaultConfig {
        minSdk = Configs.minSdkVersion
        targetSdk = Configs.targetSdkVersion
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

    implement(ModuleDependencies.domainModuleLibraries)
    kapt(Dependencies.daggerHiltCompiler)
}
