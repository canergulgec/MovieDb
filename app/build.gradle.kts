import extension.appDebugger
import extension.daggerHilt
import extension.integrationTest

plugins {
    id(Configs.androidApplication)
    id(Configs.kotlinAndroid)
    id(Configs.daggerHilt)
    id(Configs.kotlinKapt)
}

android {
    compileSdk = Versions.App.compileSdkVersion

    defaultConfig {
        applicationId = Configs.applicationId
        minSdk = Versions.App.minSdkVersion
        targetSdk = Versions.App.targetSdkVersion
        versionCode = Versions.App.versionCode
        versionName = Versions.App.versionName
        testInstrumentationRunner = Configs.hiltTestInstrumentationRunner
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
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
    integrationTest()
    appDebugger()

    implementation(Dependencies.Google.material)
    implementation(Dependencies.AndroidX.startup)

    kaptAndroidTest(Dependencies.Dagger.daggerHiltCompiler)
}
