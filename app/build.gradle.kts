import java.io.File
import java.io.FileInputStream
import java.util.Properties
import extension.*

plugins {
    id(Configs.androidApplication)
    id(Configs.daggerHilt)
    kotlin(Configs.kotlinAndroid)
    kotlin(Configs.kotlinKapt)
    id(Configs.safeArgs)
}

android {
    compileSdk = Versions.App.compileSdkVersion
    buildToolsVersion = Configs.buildToolsVersion

    defaultConfig {
        applicationId = Configs.applicationId
        minSdk = Versions.App.minSdkVersion
        targetSdk = Versions.App.targetSdkVersion
        multiDexEnabled = true
        versionCode = Versions.App.versionCode
        versionName = Versions.App.versionName
        vectorDrawables {
            useSupportLibrary = true
        }
        testInstrumentationRunner = Configs.hiltTestInstrumentationRunner

        val secureProps = Properties().apply {
            load(FileInputStream(File(rootProject.rootDir, "secure.properties")))
        }

        buildConfigField("Integer", "TIMEOUT", "60")
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "API_KEY", (secureProps.getProperty("MOVIE_API_KEY") ?: ""))
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

    // Open data binding
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

kapt {
    correctErrorTypes = true
}

configurations.all {
    resolutionStrategy {
        force("androidx.test:monitor:1.4.0")
    }
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.data))
    implementation(project(Modules.domain))
    implementation(project(Modules.presentation))

    appCompat()
    lifeCycle()
    retrofit()
    daggerHilt()
    integrationTest()
    unitTest()
    appDebugger()

    implementation(Dependencies.AndroidX.constraintLayout)
    implementation(Dependencies.AndroidX.fragmentKtx)
    implementation(Dependencies.AndroidX.multiDex)
    implementation(Dependencies.AndroidX.vectorDrawable)
    implementation(Dependencies.AndroidX.navigationFragmentKtx)
    implementation(Dependencies.AndroidX.navigationUiKtx)
    implementation(Dependencies.AndroidX.paging)
    implementation(Dependencies.AndroidX.dataStore)
    implementation(Dependencies.AndroidX.startup)
    implementation(Dependencies.AndroidX.splashScreen)
    implementation(Dependencies.AndroidX.workManager)
    implementation(Dependencies.Google.material)
    implementation(Dependencies.Google.flexBox)
    implementation(Dependencies.Dagger.hiltWorkManager)
    implementation(Dependencies.materialDialog)

    kaptAndroidTest(Dependencies.Dagger.daggerHiltCompiler)
}
