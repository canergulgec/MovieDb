plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

object PluginVersions {
    const val gradleVersion = "7.2.1"
    const val kotlinVersion = "1.7.0"
    const val updateDependenciesVersion = "0.42.0"
    const val ktlintVersion = "11.0.0"
}

dependencies {
    implementation("com.android.tools.build:gradle:${PluginVersions.gradleVersion}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginVersions.kotlinVersion}")
    implementation("com.github.ben-manes:gradle-versions-plugin:${PluginVersions.updateDependenciesVersion}")
    implementation("org.jlleitschuh.gradle:ktlint-gradle:${PluginVersions.ktlintVersion}")

    implementation("com.squareup:javapoet:1.13.0")
}
