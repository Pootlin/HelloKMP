import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.kotlin.dsl.exclude

object Versions {
    val min_sdk = 21
    val target_sdk = 29
    val compile_sdk = 29

    val kotlin = "1.3.71"
    val android_x = "1.1.0"
    val buildToolsVersion = "29.0.3"
    val junit = "4.12"
    val sqlDelight = "1.2.1"
    val ktor = "1.3.2"
    val stately = "0.9.5"
    val multiplatformSettings = "0.5"
    val coroutines = "1.3.5-native-mt"
    val koin = "3.0.1-khan-SNAPSHOT"
    val serialization = "0.14.0"
    val xcodesync = "0.2"
    val kodein = "6.5.4"
}

object Deps {
    val app_compat_x = "androidx.appcompat:appcompat:1.1.0"
    val core_ktx = "androidx.core:core-ktx:1.2.0"
    val constraintlayout = "androidx.constraintlayout:constraintlayout:1.1.3"
    val junit = "junit:junit:${Versions.junit}"
    val android_gradle_plugin = "com.android.tools.build:gradle:3.6.2"
    val stately = "co.touchlab:stately:${Versions.stately}"
    val multiplatformSettings =
        "com.russhwolf:multiplatform-settings:${Versions.multiplatformSettings}"
    val multiplatformSettingsTest =
        "com.russhwolf:multiplatform-settings-test:${Versions.multiplatformSettings}"
    val koinCore = "co.touchlab:koin-core:${Versions.koin}"
    val xcodesync = "co.touchlab:kotlinxcodesync:${Versions.xcodesync}"
    val kodeinCore = "org.kodein.di:kodein-di-erased:${Versions.kodein}"

    object AndroidXTest {
        val core = "androidx.test:core:${Versions.android_x}"
        val junit = "androidx.test.ext:junit:1.1.1"
        val runner = "androidx.test:runner:${Versions.android_x}"
        val rules = "androidx.test:rules:${Versions.android_x}"
        val espressoCore = "androidx.test.espresso:espresso-core:3.2.0"
    }

    object KotlinTest {
        val common = "org.jetbrains.kotlin:kotlin-test-common:${Versions.kotlin}"
        val annotations = "org.jetbrains.kotlin:kotlin-test-annotations-common:${Versions.kotlin}"
        val jvm = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}"
        val junit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
        val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    }

    object Coroutines {
        val common = "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${Versions.coroutines}"
        val jdk = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        val native = "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:${Versions.coroutines}"
        val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }

    object SqlDelight {
        val gradle = "com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelight}"
        val runtime = "com.squareup.sqldelight:runtime:${Versions.sqlDelight}"
        val runtimeJdk = "com.squareup.sqldelight:runtime-jvm:${Versions.sqlDelight}"
        val driverIos = "com.squareup.sqldelight:ios-driver:${Versions.sqlDelight}"
        val driverAndroid = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"
    }

    object ktor {
        val commonCore = "io.ktor:ktor-client-core:${Versions.ktor}"
        val commonJson = "io.ktor:ktor-client-json:${Versions.ktor}"
        val jvmCore = "io.ktor:ktor-client-core-jvm:${Versions.ktor}"
        val androidCore = "io.ktor:ktor-client-android:${Versions.ktor}"
        val okHttpCore = "io.ktor:ktor-client-okhttp:${Versions.ktor}"
        val jvmJson = "io.ktor:ktor-client-json-jvm:${Versions.ktor}"
        val ios = "io.ktor:ktor-client-ios:${Versions.ktor}"
        val iosCore = "io.ktor:ktor-client-core-native:${Versions.ktor}"
        val iosJson = "io.ktor:ktor-client-json-native:${Versions.ktor}"
        val commonSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
        val androidSerialization = "io.ktor:ktor-client-serialization-jvm:${Versions.ktor}"
        val iosSerialization = "io.ktor:ktor-client-serialization-native:${Versions.ktor}"
        val commonLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
        val androidLogging = "io.ktor:ktor-client-logging-jvm:${Versions.ktor}"
        val iosLogging = "io.ktor:ktor-client-logging-native:${Versions.ktor}"
    }

    val coroutinesExcludeNative: ExternalModuleDependency.() -> Unit = {
        exclude(group = "org.jetbrains.kotlinx", module = "kotlinx-coroutines-core-native")
    }
}
