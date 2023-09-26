rootProject.name = "test-deploy"

// Gradle plugin repositories
pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

// Java project repositories
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        mavenCentral()
    }
}