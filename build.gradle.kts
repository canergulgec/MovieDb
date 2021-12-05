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
        classpath(ClassPaths.gradle)
        classpath(ClassPaths.kotlinGradlePlugin)
        classpath(ClassPaths.daggerHiltGradlePlugin)
        classpath(ClassPaths.safeArgsGradlePlugin)
        /*   classpath("com.diffplug.spotless:spotless-plugin-gradle:5.11.1")
           classpath("org.jlleitschuh.gradle:ktlint-gradle:10.0.0")
           classpath("com.github.ben-manes:gradle-versions-plugin:0.29.0")*/
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

subprojects {
    /* apply(plugin = "../buildscripts/ktlint.gradle")
     apply(plugin = "../buildscripts/spotless.gradle")
     apply(plugin = "../buildscripts/versionsplugin.gradle")*/
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}