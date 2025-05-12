import extension.daggerHilt

plugins {
    id(Configs.androidLibrary)
    id(Configs.kotlinAndroid)
    id(Configs.kotlinKapt)
    id(Configs.daggerHilt)
}

android {
    namespace = "com.caner.navigation"
    compileSdk = Versions.App.compileSdkVersion

    defaultConfig {
        minSdk = Versions.App.minSdkVersion
        testInstrumentationRunner = Configs.androidInstrumentationRunner
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    daggerHilt()
    implementation("androidx.navigation:navigation-runtime-ktx:2.5.2")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
