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
    namespace = "com.caner.domain"
    compileSdk = Versions.App.compileSdkVersion

    defaultConfig {
        minSdk = Versions.App.minSdkVersion
        testInstrumentationRunner = Configs.androidInstrumentationRunner
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

kapt {
    generateStubs = true
}

dependencies {
    daggerHilt()
    unitTest()

    implementation(Dependencies.Network.moshi)
    kapt(Dependencies.Network.moshiKotlinCodegen)

    implementation(Dependencies.AndroidX.paging)
    implementation(Dependencies.AndroidX.dataStore)
}
