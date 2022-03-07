plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinParcelize)
    id(Plugins.kotlinKapt)
}

applyAndroid()

dependencies {
    implementation(project(":entity"))
    implementation(project(":feature:employee-details:impl"))
    implementation(project(":core:const"))

    implementation(Dependencies.fragment)
    modo()
}
