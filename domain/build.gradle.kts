import extension.domainModuleLibraries
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
}

kapt {
    generateStubs = true
}

dependencies {
    implementation(project(Modules.data))
    implementation(project(Modules.core))

    implement(domainModuleLibraries)
    kapt(Dependencies.Dagger.daggerHiltCompiler)
}
