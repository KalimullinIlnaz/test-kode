plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
}

applyAndroid(
    useViewBinding = true
)

dependencies {
    implementation(project(":core:stdlib"))

    implementation(Dependencies.appCompat)
    implementation(Dependencies.fragment)
    implementation(DependenciesLists.glide)
}
