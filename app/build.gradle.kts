import extension.appDebugger
import extension.daggerHilt

plugins {
    id(Configs.androidApplication)
    id(Configs.kotlinAndroid)
    id(Configs.daggerHilt)
    id(Configs.kotlinKapt)
}

android {
    namespace = "com.caner.moviedb"
    compileSdk = Versions.App.compileSdkVersion

    defaultConfig {
        applicationId = Configs.applicationId
        minSdk = Versions.App.minSdkVersion
        targetSdk = Versions.App.targetSdkVersion
        versionCode = Versions.App.versionCode
        versionName = Versions.App.versionName
        testInstrumentationRunner = Configs.androidInstrumentationRunner
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
        buildConfig = true
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(project(Modules.data))
    implementation(project(Modules.domain))
    implementation(project(Modules.navigation))
    implementation(project(Modules.presentation))

    daggerHilt()
    appDebugger()

    implementation(Dependencies.Google.material)
    implementation(Dependencies.AndroidX.startup)
}
