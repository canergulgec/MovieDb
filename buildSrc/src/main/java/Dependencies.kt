object Dependencies {

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appCompatVersion}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.AndroidX.coreKtxVersion}"
        const val navigationFragmentKtx =
            "androidx.navigation:navigation-fragment-ktx:${Versions.AndroidX.navigationVersion}"
        const val navigationUiKtx =
            "androidx.navigation:navigation-ui-ktx:${Versions.AndroidX.navigationVersion}"
        const val fragmentKtx =
            "androidx.fragment:fragment-ktx:${Versions.AndroidX.fragmentKtxVersion}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraintLayoutVersion}"
        const val dataStore =
            "androidx.datastore:datastore-preferences:${Versions.AndroidX.dataStoreVersion}"
        const val startup = "androidx.startup:startup-runtime:${Versions.AndroidX.startupVersion}"
        const val paging = "androidx.paging:paging-runtime-ktx:${Versions.AndroidX.pagingVersion}"
        const val viewPager = "androidx.viewpager2:viewpager2:${Versions.AndroidX.viewPagerVersion}"
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
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Network.retrofitVersion}"
        const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.Network.okHttpVersion}"
        const val okHttpInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.Network.okHttpVersion}"
        const val moshi = "com.squareup.moshi:moshi:${Versions.Network.moshiVersion}"
        const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.Network.moshiVersion}"
        const val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.Network.moshiVersion}"
        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.Network.retrofitVersion}"
    }

    object Debugging {
        const val stetho = "com.facebook.stetho:stetho:${Versions.Debugging.stethoVersion}"
        const val stethoOkHttp =
            "com.facebook.stetho:stetho-okhttp3:${Versions.Debugging.stethoVersion}"
        const val timber = "com.jakewharton.timber:timber:${Versions.Debugging.timberVersion}"
    }

    object ImageLoader {
        const val coil = "io.coil-kt:coil:${Versions.ImageLoader.coilVersion}"
    }

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
    }
}
