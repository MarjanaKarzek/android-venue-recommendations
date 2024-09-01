plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.jetbrains.kotlin.android)
}

android {
  namespace = "com.karzek.core"
  compileSdk = 34

  defaultConfig {
    minSdk = 24

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
  kotlinOptions {
    jvmTarget = "17"
  }
}

dependencies {
  implementation(platform(libs.koin.bom))
  implementation(libs.koin.android)
  implementation(libs.koin.core)
  implementation(libs.retrofit)
  implementation(libs.retrofit.moshi)
  implementation(platform(libs.okhttp.bom))
  implementation(libs.okhttp)
  implementation(libs.okhttp.logging.interceptor)
  implementation(libs.moshi)
  implementation(libs.moshi.adapters)
  implementation(libs.moshi.kotlin)
  implementation(libs.moshi.kotlin.codegen)
  implementation(libs.timber)
  debugImplementation(libs.chucker)
  releaseImplementation(libs.chucker.no.op)
}