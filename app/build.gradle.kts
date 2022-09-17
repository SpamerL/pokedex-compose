@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
}

android {

    compileSdk = 31

    defaultConfig {
        applicationId = "com.spamerl.pokedex_compose"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeVers.get()
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    implementation(libs.hilt)
    kapt(libs.hilt.compiller)
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation(libs.lifecycle.runtimeKtx)
    implementation(libs.lifecycle.viewModel)
    implementation(libs.compose.activity)
    implementation(libs.compose.ui)
    implementation(libs.compose.uiToolingPreview)
    implementation(libs.compose.material)
    implementation(libs.compose.navigation)
    implementation(libs.compose.navigationHilt)
}

/*
implementation 'androidx.core:core-ktx:1.7.0'
    implementation 'androidx.appcompat:appcompat:1.3.1'
    implementation 'com.google.android.material:material:1.4.0'
    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.4.0'
    implementation 'androidx.activity:activity-compose:1.4.0'

    //compose
    implementation "androidx.compose.ui:ui:$compose_version"
    // Tooling support (Previews, etc.)
    implementation "androidx.compose.ui:ui-tooling:$compose_version"
    implementation "androidx.compose.ui:ui-tooling-preview:$compose_version"
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation "androidx.compose.foundation:foundation:$compose_version"
    // Material Design
    implementation "androidx.compose.material:material:$compose_version"
    // Material design icons
    implementation "androidx.compose.material:material-icons-core:$compose_version"
    implementation "androidx.compose.material:material-icons-extended:$compose_version"

    //navigation compose
    implementation "androidx.navigation:navigation-compose:2.4.0-beta01"

    //coroutines
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2"

    //Room
    implementation "androidx.room:room-runtime:$room_version"
    implementation 'androidx.startup:startup-runtime:1.1.0'
    kapt "androidx.room:room-compiler:$room_version"
    implementation "androidx.room:room-ktx:$room_version"
    implementation "androidx.room:room-paging:2.4.0-beta01"

    //paging
    implementation "androidx.paging:paging-runtime-ktx:3.0.1"
    implementation "androidx.paging:paging-compose:1.0.0-alpha14"
    // alternatively - without Android dependencies for tests
    testImplementation "androidx.paging:paging-common-ktx:3.0.1"

    //hilt
    implementation "com.google.dagger:hilt-android:2.38.1"
    kapt "com.google.dagger:hilt-compiler:2.38.1"
    implementation "androidx.hilt:hilt-navigation-compose:1.0.0-alpha03"

    //coil-compose
    implementation "io.coil-kt:coil-compose:1.3.2"

    //okHttp
    implementation "com.squareup.okhttp3:okhttp:5.0.0-alpha.2"
    implementation "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2"

    //okio
    implementation "com.squareup.okio:okio:3.0.0-alpha.10"

    //retrofit
    implementation "com.squareup.retrofit2:retrofit:2.9.0"
    implementation "com.squareup.retrofit2:converter-gson:2.9.0"

    //gson
    implementation "com.google.code.gson:gson:2.8.8"

    //timber
    implementation "com.jakewharton.timber:timber:5.0.1"

    // startup
    implementation "androidx.startup:startup-runtime:1.1.0"



    testImplementation 'junit:junit:'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
    androidTestImplementation "androidx.compose.ui:ui-test-junit4:$compose_version"
    debugImplementation "androidx.compose.ui:ui-tooling:$compose_version"
 */
