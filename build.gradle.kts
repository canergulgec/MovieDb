import plugins.BuildPlugins

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
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
    }
}

plugins.apply(BuildPlugins.UPDATE_DEPENDENCIES)
plugins.apply(BuildPlugins.KTLINT)

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

subprojects {
    plugins.apply(BuildPlugins.KTLINT)
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}
