import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("android.extensions")
    id("com.android.library")
    id("kotlinx-serialization")
}

androidExtensions {
    isExperimental = true
}

android {
    compileSdkVersion(Versions.compile_sdk)
    buildToolsVersion = Versions.buildToolsVersion

    defaultConfig {
        minSdkVersion(Versions.min_sdk)
        targetSdkVersion(Versions.target_sdk)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    sourceSets["main"].setRoot("src/androidMain")
    sourceSets["test"].setRoot("src/androidUnitTest")
    sourceSets["androidTest"].setRoot("src/androidTest")

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }

}

kotlin {
    android()
    //Revert to just ios() when gradle plugin can properly resolve it
    val iOSTarget: (String) -> KotlinNativeTarget = if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
        ::iosArm64
    else
        ::iosX64
    iOSTarget("ios")
    targets.getByName<KotlinNativeTarget>("ios").compilations["main"].kotlinOptions.freeCompilerArgs +=
        listOf("-Xobjc-generics", "-Xg0")

    version = "1.0"

    sourceSets {
        all {
            languageSettings.apply {
                useExperimentalAnnotation("kotlin.RequiresOptIn")
                useExperimentalAnnotation("kotlinx.coroutines.ExperimentalCoroutinesApi")
            }
        }
    }

    sourceSets["commonMain"].dependencies {
        implementation(kotlin("stdlib-common", Versions.kotlin))
        implementation(Deps.ktor.commonCore)
        implementation(Deps.ktor.commonJson)
        implementation(Deps.ktor.commonSerialization)
        implementation(Deps.ktor.commonLogging)
        implementation(Deps.kodeinCore)
        implementation(Deps.Coroutines.common)
    }

    sourceSets["commonTest"].dependencies {
    }

    sourceSets["androidMain"].dependencies {
        implementation(kotlin("stdlib", Versions.kotlin))
        implementation(Deps.ktor.okHttpCore)
        implementation(Deps.ktor.jvmJson)
        implementation(Deps.ktor.androidSerialization)
        implementation(Deps.ktor.androidLogging)
        implementation(Deps.Coroutines.android)
        val lifecycleVersion = "2.2.0"
        api("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
        api("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
        api("androidx.lifecycle:lifecycle-livedata-core:$lifecycleVersion")
        implementation("com.squareup.okhttp3:logging-interceptor:4.4.1")
    }

    sourceSets["androidTest"].dependencies {
        implementation(Deps.KotlinTest.junit)
        implementation(Deps.AndroidXTest.espressoCore)
    }

    sourceSets["iosMain"].dependencies {
        implementation(Deps.ktor.ios)
        implementation(Deps.ktor.iosCore)
        implementation(Deps.ktor.iosJson)
        implementation(Deps.ktor.iosLogging)
        implementation(Deps.ktor.iosSerialization)
        implementation(Deps.Coroutines.native)
    }

    cocoapods {
        summary = "Common library"
        homepage = "https://github.com/epool/HelloKMP"
        frameworkName = "HelloKMP"
    }
}

val iOSTest: Task by tasks.creating {
    val device = project.findProperty("iosDevice")?.toString() ?: "iPhone 8"
    dependsOn("linkDebugTestIos")
    group = JavaBasePlugin.VERIFICATION_GROUP
    description = "Runs tests for target 'ios' on an iOS simulator"

    doLast {
        val binary = kotlin.targets.getByName<KotlinNativeTarget>("ios").binaries.getTest("DEBUG").outputFile
        exec {
            commandLine("xcrun", "simctl", "spawn", "--standalone", device, binary.absolutePath)
        }
    }
}