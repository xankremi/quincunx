plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("languageConventions") {
            id = "buildlogic.language-conventions"
            implementationClass = "org.xankremi.quincunx.buildlogic.plugin.LanguageConventionsPlugin"
        }

        register("unitTestingConventions") {
            id = "buildlogic.unit-testing-conventions"
            implementationClass = "org.xankremi.quincunx.buildlogic.plugin.UnitTestingConventionsPlugin"
        }

        register("integrationTestingConventions") {
            id = "buildlogic.integration-testing-conventions"
            implementationClass = "org.xankremi.quincunx.buildlogic.plugin.IntegrationTestingConventionsPlugin"
        }

        register("eventDrivenConventions") {
            id = "buildlogic.event-driven-conventions"
            implementationClass = "org.xankremi.quincunx.buildlogic.plugin.EventDrivenConventionsPlugin"
        }

        register("microserviceConventions") {
            id = "buildlogic.microservice-conventions"
            implementationClass = "org.xankremi.quincunx.buildlogic.plugin.MicroserviceConventionsPlugin"
        }
    }
}




