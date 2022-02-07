plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
}

applyAndroid()

dependencies {
    implementation(Dependencies.appCompat)
    implementation(DependenciesLists.modo)
}