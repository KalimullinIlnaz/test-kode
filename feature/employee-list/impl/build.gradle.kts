plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.daggerHiltAndroid)
    id(Plugins.kotlinParcelize)
}

applyAndroid()

dependencies {
    implementation(project(":entity"))
    implementation(project(":core:uikit"))
    implementation(project(":core:mvi"))
    implementation(project(":core:coroutines"))

    implementation(Dependencies.appCompat)
    implementation(Dependencies.fragment)
    implementation(Dependencies.lifecycleRuntime)
    implementation(Dependencies.lifecycleViewModel)
    implementation(Dependencies.modo)
    implementation(Dependencies.modoRenderAndroidFm)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.moshi)
    kapt(Dependencies.moshiKapt)
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltAndroidCompiler)
    implementation(Dependencies.adapterDelegate)
    implementation(Dependencies.recyclerView)
    implementation(Dependencies.material)
}
