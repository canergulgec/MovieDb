package extension

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implement(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplement(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplement(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}

fun DependencyHandler.debugImplement(list: List<String>) {
    list.forEach { dependency ->
        add("debugImplementation", dependency)
    }
}
