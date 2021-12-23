plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
}

applyAndroid()

dependencies {
    implementation(project(":entity"))

    implementation(Dependencies.coroutines)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.moshi)
    implementation(Dependencies.timber)
}