plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinParcelize)
}

applyAndroid()

dependencies {
    implementation(project(":feature:employee-list:impl"))
    implementation(Dependencies.fragment)
    implementation(DependenciesLists.modo)
}