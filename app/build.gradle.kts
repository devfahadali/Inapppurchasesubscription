plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.example.inapppurchasesubscription"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.inapppurchasesubscription"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    api ("com.intuit.sdp:sdp-android:1.1.0")
    api ("com.intuit.ssp:ssp-android:1.1.0")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    api("org.jetbrains.kotlin:kotlin-reflect:1.8.22")
//    implementation ("com.google.code.gson:gson:2.9.0")

    /*
  google billing library
  */
    implementation("com.android.billingclient:billing:7.0.0")

    /**
     * Koin dependencies
     */

    implementation ("io.insert-koin:koin-android:3.5.3")
    testImplementation ("io.insert-koin:koin-test:3.1.5")
    implementation("io.insert-koin:koin-androidx-compose:3.5.3")


}