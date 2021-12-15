 plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
     id(Plugins.kotlinKapt)
}

applyAndroid(
    useViewBinding = true
)

dependencies {
    implementation(Dependencies.appCompat)
    implementation(Dependencies.fragment)
    implementation(DependenciesLists.glide)
}
