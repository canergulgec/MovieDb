import extension.appCompat
import extension.daggerHilt

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

    appCompat()
    daggerHilt()

    implementation(Dependencies.AndroidX.constraintLayout)
    implementation(Dependencies.AndroidX.lifecycleLiveData)
    implementation(Dependencies.AndroidX.paging)
    implementation(Dependencies.AndroidX.viewPager)
    implementation(Dependencies.AndroidX.dataStore)
    implementation(Dependencies.AndroidX.startup)
    implementation(Dependencies.Google.material)
    implementation(Dependencies.Dagger.hiltWorkManager)
    implementation(Dependencies.Debugging.timber)
    implementation(Dependencies.ImageLoader.coil)
}
