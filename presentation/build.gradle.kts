import extension.implement
import extension.presentationModuleLibraries

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
    implementation(project(Modules.domain))
    implementation(project(Modules.data))
    implementation(project(Modules.core))

    implement(presentationModuleLibraries)
    kapt(Dependencies.Dagger.daggerHiltCompiler)
}
