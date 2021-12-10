plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
}

applyAndroid()

android {
    room {
        schemaLocationDir.set(file("roomSchemas"))
    }
}

dependencies {
    implementation(Dependencies.room)
    implementation(Dependencies.roomKtx)
    kapt(Dependencies.roomKapt)
}