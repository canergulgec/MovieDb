import extension.coreModuleLibraries
import extension.implement

plugins {
    id(Configs.androidLibrary)
    id(Configs.daggerHilt)
    kotlin(Configs.kotlinAndroid)
    kotlin(Configs.kotlinKapt)
}

android {
    compileSdk = Versions.App.compileSdkVersion

    defaultConfig {
        minSdk = Versions.App.minSdkVersion
        targetSdk = Versions.App.targetSdkVersion
        testInstrumentationRunner = Configs.androidInstrumentationRunner
    }

    buildFeatures {
        dataBinding = true
    }
}

kapt {
    generateStubs = true
}

dependencies {
    implement(coreModuleLibraries)
    kapt(Dependencies.Dagger.daggerHiltCompiler)
}
