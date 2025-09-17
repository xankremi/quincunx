package org.xankremi.quincunx.buildlogic.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * Integration testing (integrations, containers)
 */
class IntegrationTestingConventionsPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
                add("testImplementation", platform(libs.findLibrary("testcontainers-bom").get()))
                add("testImplementation", libs.findLibrary("testcontainers-junit").get())
            }
        }
    }
}
