plugins {
    id(Plugins.javaLibrary)
    id(Plugins.kotlin)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(AppConfig.javaVersion))
}