package org.xankremi.quincunx.buildlogic.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

/**
 * Basic language settings (Kotlin, compiler, JVM)
 */
class LanguageConventionsPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            plugins.apply("org.jetbrains.kotlin.jvm")
            plugins.apply("java-library")

            configureKotlinCompiler()
            configureJavaKotlinIntegration()
        }
    }

    private fun Project.configureKotlinCompiler() {
        val jvmTarget: JvmTarget = JvmTarget.JVM_21

        val commonCompilerArgs = listOf(
            "-Xjsr305=strict",
            "-Xemit-jvm-type-annotations",
            "-Xcontext-receivers",
            "-opt-in=kotlin.RequiresOptIn",
            "-Xinline-classes"
        )

        extensions.configure<KotlinJvmProjectExtension> {
            compilerOptions {
                this.jvmTarget.set(jvmTarget)
                freeCompilerArgs.addAll(commonCompilerArgs)
                allWarningsAsErrors.set(true)
                progressiveMode.set(true)
            }
            jvmToolchain(jvmTarget.target.toInt())
        }
    }

    private fun Project.configureJavaKotlinIntegration() {
        tasks.named("compileJava").configure {
            dependsOn(tasks.named("compileKotlin"))
        }

        tasks.withType(JavaCompile::class.java).configureEach {
            options.compilerArgs.addAll(
                listOf(
                    "-Xlint:unchecked",
                    "-Xlint:deprecation",
                    "-parameters"
                )
            )
        }
    }
}
