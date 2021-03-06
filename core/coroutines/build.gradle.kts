plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
}

applyAndroid()

dependencies {
    implementation(Dependencies.coroutines)
    implementation(Dependencies.timber)
    implementation(Dependencies.fragment)
    implementation(Dependencies.lifecycleRuntime)
}