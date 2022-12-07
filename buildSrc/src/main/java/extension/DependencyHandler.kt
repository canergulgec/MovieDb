package extension

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.retrofit() {
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.gsonConverter)
    implementation(Dependencies.Network.okHttp)
    implementation(Dependencies.Network.okHttpInterceptor)
}

fun DependencyHandlerScope.daggerHilt() {
    implementation(Dependencies.Dagger.daggerHilt)
    kapt(Dependencies.Dagger.daggerHiltCompiler)
}

fun DependencyHandlerScope.unitTest() {
    testImplementation(Dependencies.Test.UnitTest.junit4)
    testImplementation(Dependencies.Test.UnitTest.coroutinesTest)
    testImplementation(Dependencies.Test.UnitTest.archTesting)
    testImplementation(Dependencies.Test.UnitTest.truth)
    testImplementation(Dependencies.Test.UnitTest.turbine)
    testImplementation(Dependencies.Test.UnitTest.mockK)
}

fun DependencyHandlerScope.appDebugger() {
    implementation(Dependencies.Debugging.timber)
    debugImplementation(Dependencies.Debugging.flipper)
    debugImplementation(Dependencies.Debugging.flipperNetworkPlugin)
    debugImplementation(Dependencies.Debugging.flipperSoloader)
    debugImplementation(Dependencies.Debugging.stetho)
    debugImplementation(Dependencies.Debugging.stethoOkHttp)
}

fun DependencyHandlerScope.appCompat() {
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.coreKtx)
}

private fun DependencyHandler.implementation(depName: String) {
    add("implementation", depName)
}

private fun DependencyHandler.androidTestImplementation(depName: String) {
    add("androidTestImplementation", depName)
}

private fun DependencyHandler.testImplementation(depName: String) {
    add("testImplementation", depName)
}

private fun DependencyHandler.debugImplementation(depName: String) {
    add("debugImplementation", depName)
}

private fun DependencyHandler.kapt(depName: String) {
    add("kapt", depName)
}
