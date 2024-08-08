import com.google.protobuf.gradle.id
import com.google.protobuf.gradle.remove

plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.jetbrains.kotlin.android)
  alias(libs.plugins.protobuf.plugin)
}

android {
  namespace = "com.karzek.wishlist"
  compileSdk = 34

  defaultConfig {
    minSdk = 24

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
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
    jvmTarget = "1.8"
  }
}

protobuf {
  protoc {
    artifact = "com.google.protobuf:protoc:3.24.1"
  }
  generateProtoTasks {
    all().forEach { task ->
      task.builtins {
        id("java") {
          option("lite")
        }
      }
    }
  }
}

dependencies {
  implementation(project(":core"))
  implementation(project(":domain"))

  implementation(platform(libs.koin.bom))
  implementation(libs.koin.android)
  implementation(libs.koin.core)
  implementation(libs.androidx.core.ktx)
  implementation(libs.moshi)
  implementation(libs.datastore)
  implementation(libs.datastore.core)
  implementation(libs.protobuf.javalite)
}