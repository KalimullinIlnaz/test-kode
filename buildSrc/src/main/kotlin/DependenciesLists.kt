import org.gradle.kotlin.dsl.DependencyHandlerScope

object DependenciesLists {
    val hilt = listOf(
        Dependency(Dependencies.hiltAndroid),
        Dependency(Dependencies.hiltAndroidXCompiler),
        Dependency(Dependencies.hiltAndroidCompiler, DependencyType.KAPT)
    )
    val room = listOf(
        Dependency(Dependencies.room),
        Dependency(Dependencies.roomKtx),
        Dependency(Dependencies.roomKapt, DependencyType.KAPT)
    )
    val modo = listOf(
        Dependency(Dependencies.modo),
        Dependency(Dependencies.modoRenderAndroidFm)
    )
    val moshi = listOf(
        Dependency(Dependencies.moshi),
        Dependency(Dependencies.moshiKapt, DependencyType.KAPT)
    )
    val glide = listOf(
        Dependency(Dependencies.glide),
        Dependency(Dependencies.glideCompiler, DependencyType.KAPT)
    )
}

fun DependencyHandlerScope.implementation(dependencies: List<Dependency>) {
    dependencies.forEach { dependency ->
        add(dependency.type.value, dependency.name)
    }
}

data class Dependency(
    val name: String,
    val type: DependencyType = DependencyType.IMPLEMENTATION
)

enum class DependencyType(val value: String) {
    IMPLEMENTATION("implementation"),
    KAPT("kapt")
}