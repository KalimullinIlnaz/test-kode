plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
}

applyAndroid()

dependencies {
    implementation(project(":entity"))
    implementation(project(":core:const"))

    implementation(Dependencies.coroutines)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.moshi)
}