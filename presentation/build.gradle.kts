import extension.*

plugins {
    id(Configs.androidLibrary)
    id(Configs.kotlinAndroid)
    id(Configs.kotlinKapt)
    id(Configs.daggerHilt)
    id(Configs.safeArgs)
}

android {
    namespace = "com.caner.presentation"
    compileSdk = Versions.App.compileSdkVersion

    defaultConfig {
        minSdk = Versions.App.minSdkVersion
        testInstrumentationRunner = Configs.androidInstrumentationRunner
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
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
    correctErrorTypes = true
}

dependencies {
    implementation(project(Modules.domain))
    implementation(project(Modules.navigation))

    appCompat()
    daggerHilt()
    appDebugger()
    unitTest()

    implementation(Dependencies.AndroidX.fragmentKtx)

    implementation(Dependencies.AndroidX.navigationFragmentKtx)
    implementation(Dependencies.AndroidX.navigationUiKtx)

    implementation(Dependencies.AndroidX.constraintLayout)
    implementation(Dependencies.AndroidX.paging)
    implementation(Dependencies.AndroidX.viewPager)
    implementation(Dependencies.AndroidX.dataStore)

    implementation(Dependencies.Google.material)
    implementation(Dependencies.Google.flexBox)

    implementation(Dependencies.Debugging.timber)
    implementation(Dependencies.ImageLoader.coil)
}
