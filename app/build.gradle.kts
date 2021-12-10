plugins {
    id(Plugins.application)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.daggerHiltAndroid)
    id(Plugins.kotlinParcelize)
}

android {
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        applicationId = AppConfig.applicationId

        minSdk = AppConfig.minSdkVersion
        targetSdk = AppConfig.targetSdkVersion

        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
    }
    buildTypes {
        release {
            applicationIdSuffix = ".production"
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            applicationIdSuffix = ".dev"
            isMinifyEnabled = false
            isShrinkResources = false
        }
    }

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

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(AppConfig.javaVersion))
    }

    kotlinOptions {
        jvmTarget = AppConfig.javaVersion
    }
}

dependencies {
    implementation(project(":core:uikit"))
    implementation(project(":core:coroutines"))
    implementation(project(":core:db:impl"))
    implementation(project(":core:db:api"))

    implementation(project(":feature:main:impl"))

    implementation(Dependencies.appCompat)
    
    implementation(Dependencies.timber)

    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltAndroidCompiler)

    implementation(Dependencies.coroutines)
    implementation(Dependencies.coroutinesCore)

    implementation(Dependencies.retrofit)

    implementation(Dependencies.moshi)
    kapt(Dependencies.moshiKapt)
    implementation(Dependencies.moshiRetrofitConvertor)

    implementation(Dependencies.room)
    implementation(Dependencies.roomKtx)
    kapt(Dependencies.roomKapt)

    implementation(Dependencies.modo)
    implementation(Dependencies.modoRenderAndroidFm)
}