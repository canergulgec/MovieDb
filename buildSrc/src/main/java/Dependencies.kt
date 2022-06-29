object Dependencies {

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appCompatVersion}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.AndroidX.coreKtxVersion}"
        const val lifecycleLiveData =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.AndroidX.lifecycleVersion}"
        const val lifecycleRuntime =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.lifecycleVersion}"
        const val navigationFragmentKtx =
            "androidx.navigation:navigation-fragment-ktx:${Versions.AndroidX.navigationVersion}"
        const val navigationUiKtx =
            "androidx.navigation:navigation-ui-ktx:${Versions.AndroidX.navigationVersion}"
        const val multiDex = "androidx.multidex:multidex:${Versions.AndroidX.multiDexVersion}"
        const val recyclerView =
            "androidx.recyclerview:recyclerview:${Versions.AndroidX.recyclerViewVersion}"
        const val fragmentKtx =
            "androidx.fragment:fragment-ktx:${Versions.AndroidX.fragmentKtxVersion}"
        const val vectorDrawable =
            "androidx.vectordrawable:vectordrawable:${Versions.AndroidX.vectorDrawableVersion}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraintLayoutVersion}"
        const val dataStore =
            "androidx.datastore:datastore-preferences:${Versions.AndroidX.dataStoreVersion}"
        const val startup = "androidx.startup:startup-runtime:${Versions.AndroidX.startupVersion}"
        const val cardView = "androidx.cardview:cardview:${Versions.AndroidX.cardViewVersion}"
        const val paging = "androidx.paging:paging-runtime-ktx:${Versions.AndroidX.pagingVersion}"
        const val viewPager = "androidx.viewpager2:viewpager2:${Versions.AndroidX.viewPagerVersion}"
        const val splashScreen =
            "androidx.core:core-splashscreen:${Versions.AndroidX.splashScreenVersion}"
    }

    object Google {
        const val material =
            "com.google.android.material:material:${Versions.Google.materialVersion}"
        const val flexBox = "com.google.android.flexbox:flexbox:${Versions.Google.flexBoxVersion}"
    }

    object Dagger {
        const val daggerHilt = "com.google.dagger:hilt-android:${Versions.Dagger.daggerHiltVersion}"
        const val daggerHiltCompiler =
            "com.google.dagger:hilt-compiler:${Versions.Dagger.daggerHiltVersion}"
        const val hiltWorkManager =
            "androidx.hilt:hilt-work:${Versions.Dagger.hiltWorkManagerVersion}"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Network.retrofitVersion}"
        const val gsonConverter =
            "com.squareup.retrofit2:converter-gson:${Versions.Network.gsonVersion}"
        const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.Network.okHttpVersion}"
        const val okHttpInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.Network.okHttpVersion}"
    }

    object Coroutines {
        const val coroutinesCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Coroutines.coroutinesVersion}"
    }

    object Debugging {
        const val stetho = "com.facebook.stetho:stetho:${Versions.Debugging.stethoVersion}"
        const val stethoOkHttp =
            "com.facebook.stetho:stetho-okhttp3:${Versions.Debugging.stethoVersion}"
        const val flipper = "com.facebook.flipper:flipper:${Versions.Debugging.flipperVersion}"
        const val flipperSoloader =
            "com.facebook.soloader:soloader:${Versions.Debugging.flipperSoloaderVersion}"
        const val flipperNetworkPlugin =
            "com.facebook.flipper:flipper-network-plugin:${Versions.Debugging.flipperVersion}"
        const val timber = "com.jakewharton.timber:timber:${Versions.Debugging.timberVersion}"
    }

    object ImageLoader {
        const val coil = "io.coil-kt:coil:${Versions.ImageLoader.coilVersion}"
    }

    const val materialDialog =
        "com.afollestad.material-dialogs:core:${Versions.materialDialogVersion}"

    object Test {
        object UnitTest {
            const val junit4 = "junit:junit:${Versions.Test.junit4Version}"
            const val coroutinesTest =
                "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Coroutines.coroutinesVersion}"
            const val mockK = "io.mockk:mockk:${Versions.Test.mockkVersion}"
            const val truth = "com.google.truth:truth:${Versions.Test.truthVersion}"
            const val turbine = "app.cash.turbine:turbine:${Versions.Test.turbineVersion}"
            const val archTesting =
                "androidx.arch.core:core-testing:${Versions.Test.archTestingVersion}"
        }

        object Integration {
            const val espressoCore =
                "androidx.test.espresso:espresso-core:${Versions.Test.espressoCoreVersion}"
            const val espressoContrib =
                "androidx.test.espresso:espresso-contrib:${Versions.Test.espressoCoreVersion}"
            const val kakao = "io.github.kakaocup:kakao:${Versions.Test.kakaoVersion}"
            const val daggerHiltTesting =
                "com.google.dagger:hilt-android-testing:${Versions.Dagger.daggerHiltVersion}"
            const val mockWebServer =
                "com.squareup.okhttp3:mockwebserver:${Versions.Test.mockServerVersion}"
            const val fragmentTesting =
                "androidx.fragment:fragment-testing:${Versions.Test.fragmentTestingVersion}"
            const val workManagerTesting =
                "androidx.work:work-testing:${Versions.Test.workManagerTestingVersion}"
            const val navigationTesting =
                "androidx.navigation:navigation-testing:${Versions.AndroidX.navigationVersion}"
        }
    }
}
