plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
}

applyAndroid(useViewBinding = true)

dependencies {
    implementation(project(":core:view"))
    implementation(project(":core:stdlib"))

    implementation(Dependencies.material)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.splashScreen)
}
