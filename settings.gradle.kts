pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        maven("https://jitpack.io")
        google()
        jcenter()
    }
}

rootProject.name = "My Test Application"
include(":app")
