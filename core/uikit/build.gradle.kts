plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
}

applyAndroid()

dependencies {
    implementation(project(":core:view"))

    implementation(Dependencies.material)
    implementation(Dependencies.coreKtx)
}
