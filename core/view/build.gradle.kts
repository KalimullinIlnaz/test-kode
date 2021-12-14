plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
}

applyAndroid(
    useViewBinding = true
)

dependencies {
    implementation(Dependencies.appCompat)
    implementation(Dependencies.fragment)
}
