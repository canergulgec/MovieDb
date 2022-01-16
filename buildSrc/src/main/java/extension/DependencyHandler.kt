package extension

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandler.implement(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandlerScope.integrationTest() {
    "androidTestImplementation"(Dependencies.Test.Integration.espressoCore)
    "androidTestImplementation"(Dependencies.Test.Integration.espressoContrib)
    "androidTestImplementation"(Dependencies.Test.Integration.kakao)
    "androidTestImplementation"(Dependencies.Test.Integration.daggerHiltTesting)
    "androidTestImplementation"(Dependencies.Test.Integration.fragmentTesting)
    "androidTestImplementation"(Dependencies.Test.Integration.navigationTesting)
    "androidTestImplementation"(Dependencies.Test.Integration.workManagerTesting)
}

fun DependencyHandlerScope.unitTest() {
    "testImplementation"(Dependencies.Test.UnitTest.junit4)
    "testImplementation"(Dependencies.Test.UnitTest.coroutinesTest)
    "testImplementation"(Dependencies.Test.UnitTest.archTesting)
    "testImplementation"(Dependencies.Test.UnitTest.truth)
    "testImplementation"(Dependencies.Test.UnitTest.turbine)
    "testImplementation"(Dependencies.Test.UnitTest.mockK)
}

fun DependencyHandlerScope.appDebugger() {
    "debugImplementation"(Dependencies.Debugging.flipper)
    "debugImplementation"(Dependencies.Debugging.flipperNetworkPlugin)
    "debugImplementation"(Dependencies.Debugging.flipperSoloader)
    "debugImplementation"(Dependencies.Debugging.stetho)
    "debugImplementation"(Dependencies.Debugging.stethoOkHttp)
}

val coreModuleLibraries = arrayListOf<String>().apply {
    add(Dependencies.AndroidX.appCompat)
    add(Dependencies.AndroidX.coreKtx)
    add(Dependencies.AndroidX.lifecycleLiveData)
    add(Dependencies.AndroidX.fragmentKtx)
    add(Dependencies.AndroidX.cardView)
    add(Dependencies.AndroidX.paging)
    add(Dependencies.Dagger.daggerHilt)
    add(Dependencies.Coroutines.coroutinesCore)
    add(Dependencies.ImageLoader.coil)
}

val dataModuleLibraries = arrayListOf<String>().apply {
    add(Dependencies.AndroidX.appCompat)
    add(Dependencies.AndroidX.coreKtx)
    add(Dependencies.AndroidX.recyclerView)
    add(Dependencies.AndroidX.dataStore)
    add(Dependencies.Dagger.daggerHilt)
    add(Dependencies.Network.gsonConverter)
}

val domainModuleLibraries = arrayListOf<String>().apply {
    add(Dependencies.AndroidX.appCompat)
    add(Dependencies.AndroidX.coreKtx)
    add(Dependencies.AndroidX.lifecycleLiveData)
    add(Dependencies.AndroidX.fragmentKtx)
    add(Dependencies.AndroidX.dataStore)
    add(Dependencies.AndroidX.paging)
    add(Dependencies.Network.retrofit)
    add(Dependencies.Network.gsonConverter)
    add(Dependencies.Network.okHttp)
    add(Dependencies.Network.okHttpInterceptor)
    add(Dependencies.Dagger.daggerHilt)
    add(Dependencies.Debugging.stetho)
    add(Dependencies.Debugging.stethoOkHttp)
}

val presentationModuleLibraries = arrayListOf<String>().apply {
    add(Dependencies.AndroidX.appCompat)
    add(Dependencies.AndroidX.coreKtx)
    add(Dependencies.AndroidX.constraintLayout)
    add(Dependencies.AndroidX.lifecycleLiveData)
    add(Dependencies.AndroidX.paging)
    add(Dependencies.AndroidX.viewPager)
    add(Dependencies.AndroidX.dataStore)
    add(Dependencies.AndroidX.startup)
    add(Dependencies.Google.material)
    add(Dependencies.Dagger.daggerHilt)
    add(Dependencies.Dagger.hiltWorkManager)
    add(Dependencies.Debugging.timber)
}
