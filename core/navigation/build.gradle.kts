plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
}

applyAndroid()

dependencies {
    implementation(Dependencies.appCompat)
    modo()
}
