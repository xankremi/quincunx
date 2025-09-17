package org.xankremi.quincunx.buildlogic.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * Event-driven architecture (aggregates, CQRS, events)
 */
class EventDrivenConventionsPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("implementation", platform(libs.findLibrary("axon-bom").get()))
                add("implementation", libs.findLibrary("axon-spring-boot-starter").get())
            }
        }
    }
}
