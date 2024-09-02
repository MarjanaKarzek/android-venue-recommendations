plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.jetbrains.kotlin.android)
}

android {
  namespace = "com.karzek.restaurants"
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
  testOptions {
    project.tasks.withType<Test>().configureEach {
      useJUnitPlatform()
    }
  }
}

dependencies {
  implementation(project(":core"))
  implementation(project(":domain"))

  implementation(platform(libs.koin.bom))
  implementation(libs.koin.android)
  implementation(libs.koin.core)
  implementation(libs.retrofit)
  implementation(libs.moshi)
  implementation(libs.moshi.adapters)

  testImplementation(libs.junit5)
  testImplementation(libs.junit5.params)
  testImplementation(libs.coroutines.test)
  testImplementation(libs.mockito.kotlin)
  runtimeOnly(libs.junit5.engine)
}