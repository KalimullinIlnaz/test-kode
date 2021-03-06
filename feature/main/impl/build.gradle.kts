plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.daggerHiltAndroid)
    id(Plugins.kotlinParcelize)
}

applyAndroid()

dependencies {
    implementation(project(":feature:employee-list:api"))
    implementation(project(":core:uikit"))
    implementation(project(":core:stdlib"))

    implementation(Dependencies.appCompat)
    modo()
    hilt()
    implementation(Dependencies.splashScreen)
}
