import java.util.Properties
import java.io.FileInputStream

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
        minSdkVersion(23)
        targetSdkVersion(30)
        multiDexEnabled = true
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }
        // testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "com.android.test.utils.HiltTestRunner"

    /*    val secureProps = Properties ()
        if (file("../secure.properties").exists()) {
            file("../secure.properties")?.withInputStream { secureProps.load(it) }
        }
*/
        buildConfigField("Integer", "TIMEOUT", "60")
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "API_KEY",  "")
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

    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.2")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation("androidx.fragment:fragment-ktx:1.4.0")

    // Material design
    implementation("com.google.android.material:material:1.4.0")

    // Multi Dex
    implementation("com.android.support:multidex:1.0.3")

    //Vector Drawable
    implementation("androidx.vectordrawable:vectordrawable:1.2.0-alpha02")

    // Hilt dependencies
    implementation("com.google.dagger:hilt-android:2.39.1")
    kapt("com.google.dagger:hilt-compiler:2.39.1")

    // Service Manager
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.1")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")

    // Material Dialog
    implementation("com.afollestad.material-dialogs:core:3.3.0")

    // Navigation Component
    implementation("androidx.navigation:navigation-fragment-ktx:2.4.0-beta02")
    implementation("androidx.navigation:navigation-ui-ktx:2.4.0-beta02")
    androidTestImplementation("androidx.navigation:navigation-testing:2.4.0-beta02")

    // Timber
    implementation("com.jakewharton.timber:timber:5.0.1")

    // Paging 3
    implementation("androidx.paging:paging-runtime-ktx:3.0.1")

    // Stetho
    implementation("com.facebook.stetho:stetho:1.6.0")
    implementation("com.facebook.stetho:stetho-okhttp3:1.6.0")

    // FlexBox
    implementation("com.google.android.flexbox:flexbox:3.0.0")

    // Hilt WorkManager
    implementation("androidx.hilt:hilt-work:1.0.0")
    kapt("androidx.hilt:hilt-compiler:1.0.0")

    // Preferences DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // UI Test
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.3.0")

    // Kakao
    androidTestImplementation("io.github.kakaocup:kakao:3.0.4")

    // WorkManager Test
    androidTestImplementation("androidx.work:work-testing:2.6.0")

    // MockServer
    androidTestImplementation("com.squareup.okhttp3:mockwebserver:4.9.1")

    // Hilt testing dependencies
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.39.1")
    kaptAndroidTest("com.google.dagger:hilt-compiler:2.39.1")

    debugImplementation("androidx.fragment:fragment-testing:1.3.6")
    // Create debug source set
    //

    // Unit Test
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.0")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("app.cash.turbine:turbine:0.5.2")
    testImplementation("io.mockk:mockk:1.12.0")
    //

    // Flipper
    debugImplementation("com.facebook.flipper:flipper:0.80.0")
    debugImplementation("com.facebook.soloader:soloader:0.10.1")
    debugImplementation("com.facebook.flipper:flipper-network-plugin:0.80.0")

    // App Startup
    implementation("androidx.startup:startup-runtime:1.1.0")
}

