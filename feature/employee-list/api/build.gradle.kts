plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinParcelize)
    id(Plugins.kotlinKapt)
}

applyAndroid()

dependencies {
    implementation(project(":feature:employee-list:impl"))
    implementation(Dependencies.fragment)
    modo()
}
