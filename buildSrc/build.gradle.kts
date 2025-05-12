plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

kotlin {
    jvmToolchain(17)
}

object PluginVersions {
    const val gradleVersion = "8.2.0"
    const val kotlinVersion = "1.9.22"
    const val updateDependenciesVersion = "0.51.0"
    const val ktlintVersion = "11.6.1"
}

dependencies {
    implementation("com.android.tools.build:gradle:${PluginVersions.gradleVersion}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginVersions.kotlinVersion}")
    implementation("com.github.ben-manes:gradle-versions-plugin:${PluginVersions.updateDependenciesVersion}")
    implementation("org.jlleitschuh.gradle:ktlint-gradle:${PluginVersions.ktlintVersion}")

    implementation("com.squareup:javapoet:1.13.0")
}
