package plugins

import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.KtlintPlugin
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

apply<KtlintPlugin>()

configure<KtlintExtension> {
    version.set(Versions.ktlintInternal)
    android.set(true)
    outputToConsole.set(true)
    outputColorName.set("RED")
    enableExperimentalRules.set(true)
    additionalEditorconfigFile.set(file("${project.rootDir}/.editorconfig"))
    reporters {
        reporter(ReporterType.HTML)
    }
    filter {
        exclude("**/generated/**")
        include("**/kotlin/**")
    }
}
