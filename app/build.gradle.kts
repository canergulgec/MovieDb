import java.io.File
import java.io.FileInputStream
import java.util.*

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(31)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId = "com.caner.moviedb"
        minSdk = 23
        targetSdk = 30
        multiDexEnabled = true
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }
        // testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "com.android.test.utils.HiltTestRunner"

        val secureProps = Properties().apply {
            load(FileInputStream(File(rootProject.rootDir, "secure.properties")))
        }

        buildConfigField("Integer", "TIMEOUT", "60")
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "API_KEY", (secureProps.getProperty("MOVIE_API_KEY") ?: ""))
    }

    buildTypes {
        getByName("release") {
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
    implementation(Dependencies.constraintLayoutVersion)

    // Lifecycle
    implementation(Dependencies.lifecycleLiveData)
    implementation(Dependencies.lifecycleRuntime)
    implementation(Dependencies.fragmentKtx)

    // Material design
    implementation(Dependencies.material)

    // Multi Dex
    implementation(Dependencies.multiDex)

    //Vector Drawable
    implementation(Dependencies.vectorDrawable)

    // Hilt dependencies
    implementation(Dependencies.daggerHilt)
    kapt(Dependencies.daggerHiltCompiler)

    // Service Manager
    implementation(Dependencies.retrofit)
    implementation(Dependencies.gsonConverter)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.okHttpInterceptor)

    // Material Dialog
    implementation(Dependencies.materialDialog)

    // Navigation Component
    implementation(Dependencies.navigationFragmentKtx)
    implementation(Dependencies.navigationUiKtx)
    androidTestImplementation(Dependencies.navigationTesting)

    // Timber
    implementation(Dependencies.timber)

    // Paging 3
    implementation(Dependencies.paging)

    // Stetho
    implementation(Dependencies.stetho)
    implementation(Dependencies.stethoOkHttp)

    // FlexBox
    implementation(Dependencies.flexBox)

    // Hilt WorkManager
    implementation(Dependencies.hiltWorkManager)
    kapt(Dependencies.hiltCompiler)

    // Preferences DataStore
    implementation(Dependencies.dataStore)

    // UI Test
    androidTestImplementation(Dependencies.espressoCore)
    androidTestImplementation(Dependencies.espressoContrib)

    // Kakao
    androidTestImplementation(Dependencies.kakao)

    // WorkManager Test
    androidTestImplementation(Dependencies.workManagerTesting)

    // MockServer
    androidTestImplementation(Dependencies.mockWebServer)

    // Hilt testing dependencies
    androidTestImplementation(Dependencies.daggerHiltTesting)
    kaptAndroidTest(Dependencies.daggerHiltCompiler)

    debugImplementation(Dependencies.fragmentTesting)
    // Create debug source set
    //

    // Unit Test
    testImplementation(Dependencies.junit4)
    testImplementation(Dependencies.coroutinesTest)
    testImplementation(Dependencies.archTesting)
    testImplementation(Dependencies.turbine)
    testImplementation(Dependencies.mockK)
    //

    // Flipper
    debugImplementation(Dependencies.flipper)
    debugImplementation(Dependencies.flipperSoloader)
    debugImplementation(Dependencies.flipperNetworkPlugin)

    // App Startup
    implementation(Dependencies.startup)
}

