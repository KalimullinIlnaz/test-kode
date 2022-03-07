import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.hilt() {
    implementation(Dependencies.hiltAndroid)
    implementation(Dependencies.hiltAndroidXCompiler)
    kapt(Dependencies.hiltAndroidCompiler)
}

fun DependencyHandlerScope.modo() {
    implementation(Dependencies.modo)
    implementation(Dependencies.modoRenderAndroidFm)
    kapt(Dependencies.hiltAndroidCompiler)
}

fun DependencyHandlerScope.moshi() {
    implementation(Dependencies.moshi)
    implementation(Dependencies.hiltAndroidXCompiler)
    kapt(Dependencies.moshiKapt)
}

fun DependencyHandlerScope.glide() {
    implementation(Dependencies.glide)
    kapt(Dependencies.glideCompiler)
}

fun DependencyHandlerScope.room() {
    implementation(Dependencies.room)
    implementation(Dependencies.roomKtx)
    kapt(Dependencies.roomKapt)
}

private fun DependencyHandlerScope.implementation(name: String) {
    add("implementation", name)
}

private fun DependencyHandlerScope.kapt(name: String) {
    add("kapt", name)
}
