package org.xankremi.quincunx.buildlogic.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * Unit-testing (JUnit, AssertJ, Mockito)
 */
class UnitTestingConventionsPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            plugins.apply("java-test-fixtures")

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("testImplementation", platform(libs.findLibrary("junit-bom").get()))
                add("testImplementation", libs.findLibrary("junit-jupiter").get())
                add("testImplementation", libs.findLibrary("junit-jupiter-params").get())
                add("testImplementation", libs.findLibrary("assertj").get())
                add("testImplementation", libs.findLibrary("mockito-core").get())
                add("testImplementation", libs.findLibrary("mockito-kotlin").get())
                add("testRuntimeOnly", libs.findLibrary("junit-platform-launcher").get())
            }

            tasks.withType(org.gradle.api.tasks.testing.Test::class.java).configureEach {
                useJUnitPlatform()
                testLogging {
                    events("passed", "skipped", "failed")
                }
            }
        }
    }
}
