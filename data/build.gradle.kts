import extension.appDebugger
import extension.daggerHilt
import extension.retrofit
import extension.unitTest
import java.io.File
import java.io.FileInputStream
import java.util.Properties

plugins {
    id(Configs.androidLibrary)
    id(Configs.kotlinAndroid)
    id(Configs.kotlinKapt)
    id(Configs.daggerHilt)
    id(Configs.kotlinParcelize)
}

android {
    namespace = "com.android.data"
    compileSdk = Versions.App.compileSdkVersion

    defaultConfig {
        minSdk = Versions.App.minSdkVersion
        testInstrumentationRunner = Configs.androidInstrumentationRunner

        buildConfigField("Integer", "TIMEOUT", "60")
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "API_KEY", getApiKey())
    }

    buildFeatures {
        buildConfig = true
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
    implementation(project(Modules.domain))

    daggerHilt()
    retrofit()
    appDebugger()
    unitTest()

    implementation(Dependencies.AndroidX.dataStore)
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.AndroidX.paging)

    implementation(Dependencies.Network.moshi)
    implementation(Dependencies.Network.moshiKotlin)
    kapt(Dependencies.Network.moshiKotlinCodegen)
}

fun getApiKey(): String {
    val prop = Properties().apply {
        load(FileInputStream(File(rootProject.rootDir, "./local.properties")))
    }
    return prop.getProperty("MOVIE_API_KEY")
}
