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
    }
}

plugins.apply("plugins.update-dependencies")

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

subprojects {
    plugins.apply("plugins.ktlint")
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}
