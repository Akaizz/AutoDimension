plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "gapo.autodimension.sample"
    compileSdk = 36

    defaultConfig {
        applicationId = "gapo.autodimension.sample"
        minSdk = 23
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(project(":autodimens"))
    implementation(libs.androidx.appcompat)

    testImplementation(libs.junit)
}
