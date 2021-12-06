plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

object PluginVersions {
    const val gradleVersion = "7.0.3"
    const val kotlinVersion = "1.5.31"
    const val updateDependenciesVersion = "0.39.0"
    const val ktlintVersion = "10.2.0"
}

dependencies {
    implementation("com.android.tools.build:gradle:${PluginVersions.gradleVersion}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginVersions.kotlinVersion}")
    implementation("com.github.ben-manes:gradle-versions-plugin:${PluginVersions.updateDependenciesVersion}")
    implementation("org.jlleitschuh.gradle:ktlint-gradle:${PluginVersions.ktlintVersion}")
}
