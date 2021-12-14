plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.daggerHiltAndroid)
    id(Plugins.kotlinParcelize)
}

android {
    hilt {
        enableExperimentalClasspathAggregation = true
    }
}

applyAndroid(
    useViewBinding = true
)

dependencies {
    implementation(project(":entity"))
    implementation(project(":core:uikit"))
    implementation(project(":core:mvi"))
    implementation(project(":core:coroutines"))
    implementation(project(":core:view"))

    implementation(Dependencies.appCompat)
    implementation(Dependencies.fragment)
    implementation(Dependencies.lifecycleRuntime)
    implementation(Dependencies.lifecycleViewModel)
    implementation(DependenciesLists.modo)
    implementation(Dependencies.retrofit)
    implementation(DependenciesLists.moshi)
    implementation(DependenciesLists.hilt)
    implementation(Dependencies.adapterDelegate)
    implementation(Dependencies.recyclerView)
    implementation(Dependencies.material)
}
