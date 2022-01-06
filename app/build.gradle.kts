import java.io.File
import java.io.FileInputStream
import java.util.Properties
import dependencies.Dependencies
import dependencies.TestDependencies
import dependencies.ModuleDependencies
import extension.androidTestImplement
import extension.debugImplement
import extension.testImplement

plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = Configs.compileSdkVersion
    buildToolsVersion = Configs.buildToolsVersion

    defaultConfig {
        applicationId = Configs.applicationId
        minSdk = Configs.minSdkVersion
        targetSdk = Configs.targetSdkVersion
        multiDexEnabled = true
        versionCode = Configs.versionCode
        versionName = Configs.versionName
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
    implementation(project(":core"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))

    implementation(Dependencies.appCompat)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.constraintLayout)
    implementation(Dependencies.lifecycleLiveData)
    implementation(Dependencies.lifecycleRuntime)
    implementation(Dependencies.fragmentKtx)
    implementation(Dependencies.material)
    implementation(Dependencies.multiDex)
    implementation(Dependencies.vectorDrawable)
    implementation(Dependencies.daggerHilt)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.gsonConverter)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.okHttpInterceptor)
    implementation(Dependencies.materialDialog)
    implementation(Dependencies.navigationFragmentKtx)
    implementation(Dependencies.navigationUiKtx)
    implementation(Dependencies.timber)
    implementation(Dependencies.paging)
    implementation(Dependencies.stetho)
    implementation(Dependencies.stethoOkHttp)
    implementation(Dependencies.flexBox)
    implementation(Dependencies.hiltWorkManager)
    implementation(Dependencies.dataStore)
    implementation(Dependencies.startup)
    implementation(Dependencies.splashScreen)

    kapt(Dependencies.daggerHiltCompiler)

    androidTestImplement(TestDependencies.uiTestLibraries)
    kaptAndroidTest(Dependencies.daggerHiltCompiler)

    testImplement(TestDependencies.unitTestLibraries)

    debugImplement(ModuleDependencies.debugImplementLibraries)
}
