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
    compileSdk = Versions.App.compileSdkVersion

    defaultConfig {
        minSdk = Versions.App.minSdkVersion
        targetSdk = Versions.App.targetSdkVersion
        testInstrumentationRunner = Configs.androidInstrumentationRunner

        buildConfigField("Integer", "TIMEOUT", "60")
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "API_KEY", getApiKey())
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
    implementation(Dependencies.Network.gsonConverter)
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.AndroidX.paging)
}

fun getApiKey(): String {
    val prop = Properties().apply {
        load(FileInputStream(File(rootProject.rootDir, "./local.properties")))
    }
    return prop.getProperty("MOVIE_API_KEY")
}
