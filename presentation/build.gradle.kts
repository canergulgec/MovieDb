import extension.appCompat
import extension.daggerHilt

plugins {
    id(Configs.androidLibrary)
    id(Configs.kotlinAndroid)
    id(Configs.kotlinKapt)
    id(Configs.daggerHilt)
    id(Configs.safeArgs)
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
        viewBinding = true
    }
}

kapt {
    generateStubs = true
}

dependencies {
    implementation(project(Modules.domain))
    implementation(project(Modules.core))
    implementation(project(Modules.navigation))

    appCompat()
    daggerHilt()

    implementation(Dependencies.AndroidX.navigationFragmentKtx)
    implementation(Dependencies.AndroidX.navigationUiKtx)

    implementation(Dependencies.AndroidX.constraintLayout)
    implementation(Dependencies.AndroidX.lifecycleLiveData)
    implementation(Dependencies.AndroidX.paging)
    implementation(Dependencies.AndroidX.viewPager)
    implementation(Dependencies.AndroidX.dataStore)
    implementation(Dependencies.AndroidX.startup)

    implementation(Dependencies.Google.material)
    implementation(Dependencies.Google.flexBox)

    implementation(Dependencies.Debugging.timber)
    implementation(Dependencies.ImageLoader.coil)
}
