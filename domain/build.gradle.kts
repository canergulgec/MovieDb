import extension.appCompat
import extension.daggerHilt
import extension.retrofit

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

    appCompat()
    daggerHilt()
    retrofit()

    implementation(Dependencies.AndroidX.lifecycleLiveData)
    implementation(Dependencies.AndroidX.fragmentKtx)
    implementation(Dependencies.AndroidX.dataStore)
    implementation(Dependencies.AndroidX.paging)
}
