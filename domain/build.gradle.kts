import extension.daggerHilt
import extension.unitTest

plugins {
    id(Configs.androidLibrary)
    id(Configs.kotlinAndroid)
    id(Configs.kotlinKapt)
    id(Configs.daggerHilt)
    id(Configs.kotlinParcelize)
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
    implementation(project(Modules.core))

    daggerHilt()
    unitTest()

    implementation(Dependencies.Network.gsonConverter)
    implementation(Dependencies.AndroidX.paging)
    implementation(Dependencies.AndroidX.dataStore)
}
