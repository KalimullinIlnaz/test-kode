plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
}

applyAndroid(generateBuildConfig = true)

dependencies {
    implementation(project(":core:coroutines"))

    implementation(Dependencies.timber)
    implementation(Dependencies.coroutines)
    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.viewModelKtx)
}