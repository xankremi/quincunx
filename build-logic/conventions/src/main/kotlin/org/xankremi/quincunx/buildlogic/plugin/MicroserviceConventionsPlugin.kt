package org.xankremi.quincunx.buildlogic.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * Microservice stack, Spring Boot, shared dependencies
 */
class MicroserviceConventionsPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            plugins.apply(LanguageConventionsPlugin::class.java)
            plugins.apply(UnitTestingConventionsPlugin::class.java)

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            plugins.apply(libs.findPlugin("spring-boot").get().get().pluginId)
            plugins.apply(libs.findPlugin("spring-dep-management").get().get().pluginId)

            dependencies {
                add("implementation", platform(libs.findLibrary("spring-boot-bom").get()))
                add("implementation", libs.findLibrary("spring-boot-starter").get())
                add("implementation", libs.findLibrary("spring-boot-starter-mvc").get())
                add("implementation", libs.findLibrary("spring-boot-starter-json").get())
                add("implementation", libs.findLibrary("spring-boot-starter-logging").get())

                val starterTest = libs.findLibrary("spring-boot-starter-test").get()
                val dep = create(starterTest.get()) as ExternalModuleDependency
                dep.exclude(mapOf("group" to "org.junit.vintage"))
                add("testImplementation", dep)
            }
        }
    }
}
