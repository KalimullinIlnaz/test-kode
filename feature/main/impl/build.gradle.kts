plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.daggerHiltAndroid)
    id(Plugins.kotlinParcelize)
}

applyAndroid()

dependencies {
    implementation(project(":core:uikit"))

    implementation(Dependencies.appCompat)

    implementation(Dependencies.modo)
    implementation(Dependencies.modoRenderAndroidFm)

    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltAndroidCompiler)
}
