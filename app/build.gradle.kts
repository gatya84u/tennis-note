plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.devtools.ksp") //TODO dependenciesでkspを使う可能性があったので一応定義
}
kotlin {
    jvmToolchain(17)
}
android {
    namespace = "u.akita.tennis_note"
    compileSdk = 35

    defaultConfig {
        applicationId = "u.akita.tennis_note"
        minSdk = 30
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
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
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.2")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.2")
    implementation("androidx.core:core-ktx:+")
    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("androidx.cardview:cardview:1.0.0")

    /**
     * room setup
     */
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")

    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:2.5.1")
    // To use Kotlin Symbol Processing (KSP)
    //TODO android developer siteで記載されていたが、room-compilerはkaptで定義してるので不要？
//    classpath "com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:2.0.10-1.0.24"
//    ksp("androidx.room:room-compiler:2.5.1")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:2.5.1")

    // optional - RxJava2 support for Room
    implementation("androidx.room:room-rxjava2:2.5.1")

    // optional - RxJava3 support for Room
    implementation("androidx.room:room-rxjava3:2.5.1")

    // optional - Guava support for Room, including Optional and ListenableFuture
    implementation("androidx.room:room-guava:2.5.1")

    // optional - Test helpers
    testImplementation("androidx.room:room-testing:2.5.1")

    // optional - Paging 3 Integration
    implementation("androidx.room:room-paging:2.5.1")
}