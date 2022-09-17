enableFeaturePreview("VERSION_CATALOGS")
pluginManagement {
    includeBuild("versionCatalog")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "pokedex_compose"
include(":app")
include(":core-network")
include(":core-database")
include(":core-model")
include(":core-data")
