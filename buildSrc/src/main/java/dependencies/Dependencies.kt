package dependencies

import Versions

object Dependencies {

    // Jetpack
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val lifecycleLiveData =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}"
    const val lifecycleRuntime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleVersion}"
    const val material = "com.google.android.material:material:${Versions.materialVersion}"
    const val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
    const val navigationUiKtx =
        "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}"
    const val multiDex = "androidx.multidex:multidex:${Versions.multiDexVersion}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerViewVersion}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtxVersion}"
    const val vectorDrawable =
        "androidx.vectordrawable:vectordrawable:${Versions.vectorDrawableVersion}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val dataStore = "androidx.datastore:datastore-preferences:${Versions.dataStoreVersion}"
    const val startup = "androidx.startup:startup-runtime:${Versions.startupVersion}"
    const val cardView = "androidx.cardview:cardview:${Versions.cardViewVersion}"
    const val paging = "androidx.paging:paging-runtime-ktx:${Versions.pagingVersion}"
    const val viewPager = "androidx.viewpager2:viewpager2:${Versions.viewPagerVersion}"

    // Dagger
    const val daggerHilt = "com.google.dagger:hilt-android:${Versions.daggerHiltVersion}"
    const val daggerHiltCompiler =
        "com.google.dagger:hilt-android-compiler:${Versions.daggerHiltVersion}"
    const val hiltWorkManager = "androidx.hilt:hilt-work:${Versions.hiltWorkManagerVersion}"

    // Network
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.gsonVersion}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttpVersion}"
    const val okHttpInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpVersion}"

    // Coroutines
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"

    // App Debugger
    const val stetho = "com.facebook.stetho:stetho:${Versions.stethoVersion}"
    const val stethoOkHttp = "com.facebook.stetho:stetho-okhttp3:${Versions.stethoVersion}"
    const val flipper = "com.facebook.flipper:flipper:${Versions.flipperVersion}"
    const val flipperSoloader = "com.facebook.soloader:soloader:${Versions.flipperSoloaderVersion}"
    const val flipperNetworkPlugin =
        "com.facebook.flipper:flipper-network-plugin:${Versions.flipperVersion}"

    // Other 3rd party libraries
    const val materialDialog =
        "com.afollestad.material-dialogs:core:${Versions.materialDialogVersion}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"
    const val coil = "io.coil-kt:coil:${Versions.coilVersion}"
    const val flexBox = "com.google.android.flexbox:flexbox:${Versions.flexBoxVersion}"
    const val splashScreen = "androidx.core:core-splashscreen:${Versions.splashScreenVersion}"

    // Testing
    const val junit4 = "junit:junit:${Versions.junit4Version}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
    const val espressoContrib =
        "androidx.test.espresso:espresso-contrib:${Versions.espressoCoreVersion}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesVersion}"
    const val kakao = "io.github.kakaocup:kakao:${Versions.kakaoVersion}"
    const val daggerHiltTesting =
        "com.google.dagger:hilt-android-testing:${Versions.daggerHiltVersion}"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.mockServerVersion}"
    const val fragmentTesting =
        "androidx.fragment:fragment-testing:${Versions.fragmentTestingVersion}"
    const val workManagerTesting =
        "androidx.work:work-testing:${Versions.workManagerTestingVersion}"
    const val archTesting = "androidx.arch.core:core-testing:${Versions.archTestingVersion}"
    const val turbine = "app.cash.turbine:turbine:${Versions.turbineVersion}"
    const val mockK = "io.mockk:mockk:${Versions.mockkVersion}"
    const val truth = "com.google.truth:truth:${Versions.truthVersion}"
    const val navigationTesting =
        "androidx.navigation:navigation-testing:${Versions.navigationVersion}"
}
