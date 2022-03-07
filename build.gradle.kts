import com.android.build.gradle.api.AndroidBasePlugin
import io.gitlab.arturbosch.detekt.Detekt

buildscript {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}")
        classpath("org.jmailen.gradle:kotlinter-gradle:${Versions.kotlinter}")
    }
}

plugins {
    id(Plugins.detekt) version Versions.detekt
    id(Plugins.gradleCacheFix) version Versions.gradleCacheFix apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
    apply(plugin = Plugins.kotlinter)
    apply(plugin = Plugins.detekt)
}
subprojects {
    plugins.withType<AndroidBasePlugin>() {
        apply(plugin = Plugins.gradleCacheFix)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

detekt {
    config = files("$projectDir/config/detekt.yml")
}

tasks.withType<Detekt>().configureEach {
    reports {
        html.required.set(true)
    }
    jvmTarget = "11"
}
