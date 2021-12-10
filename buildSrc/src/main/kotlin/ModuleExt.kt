import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.findByType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun Project.applyAndroid() {
    extensions.findByType<LibraryExtension>()?.apply {
        compileSdk = AppConfig.compileSdkVersion

        lint {
            isAbortOnError = true
            isIgnoreWarnings = true
            isQuiet = true
        }

        packagingOptions {
            resources.excludes.addAll(
                setOf(
                    "META-INF/DEPENDENCIES",
                    "META-INF/LICENSE",
                    "META-INF/LICENSE.txt",
                    "META-INF/license.txt",
                    "META-INF/NOTICE",
                    "META-INF/NOTICE.txt",
                    "META-INF/notice.txt",
                    "META-INF/ASL2.0",
                    "META-INF/AL2.0",
                    "META-INF/LGPL2.1",
                )
            )
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
    }

    extensions.findByType<KotlinCompile>()?.apply {
        kotlinOptions {
            jvmTarget = AppConfig.javaVersion
        }
    }

    dependencies {
        add("implementation", Dependencies.kotlinStdLib)
    }
}