plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.gradle.hilt)
    alias(libs.plugins.kotlin.kapt)
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.aoliva.metmuseum"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
        }
        getByName("debug") {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
    @Suppress("UnstableApiUsage")
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }
    namespace = "com.aoliva.metmuseum"
}

dependencies {
    // Compose
    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material3)
    debugImplementation(libs.androidx.compose.tooling)
    implementation(libs.androidx.compose.tooling.preview)

    // AndroidX
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // KTX
    implementation(libs.androidx.ktx.core)

    // Hilt
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)
    testImplementation(libs.dagger.hilt.android.testing)

    // Navigation
    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.hilt.navigation.compose)

    // Serialization
    implementation(libs.moshi)

    // Testing
    testImplementation(libs.junit)

    // Images
    implementation(libs.coil.compose)
}

kapt {
    correctErrorTypes = true
}

hilt {
    enableAggregatingTask = true
}