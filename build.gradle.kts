// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven {
            setUrl("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.39.1")
        classpath("com.diffplug.spotless:spotless-plugin-gradle:5.11.1")
        classpath("org.jlleitschuh.gradle:ktlint-gradle:10.0.0")
        classpath("com.github.ben-manes:gradle-versions-plugin:0.29.0")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.4.0-beta02")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "../buildscripts/ktlint.gradle")
    apply(plugin = "../buildscripts/spotless.gradle")
    apply(plugin = "../buildscripts/versionsplugin.gradle")
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}