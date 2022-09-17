import extension.appCompat
import extension.daggerHilt

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

    appCompat()
    daggerHilt()

    implementation(Dependencies.AndroidX.dataStore)
    implementation(Dependencies.Network.gsonConverter)
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.AndroidX.paging)
}
