plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.jetbrains.kotlin.android)
}

android {
  namespace = "com.karzek.venues"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.karzek.venues"
    minSdk = 24
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "com.karzek.venues.util.InstrumentationTestRunner"

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
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = "17"
  }
  buildFeatures {
    buildConfig = true
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.1"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
      excludes += "META-INF/LICENSE.md"
      excludes += "META-INF/LICENSE-notice.md"
    }
  }
}

dependencies {
  implementation(project(":core"))
  implementation(project(":designsystem"))
  implementation(project(":domain"))
  implementation(project(":location"))
  implementation(project(":restaurants"))
  implementation(project(":wishlist"))

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.lifecycle.runtime.compose)
  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.material3)
  implementation(platform(libs.koin.bom))
  implementation(libs.koin.core)
  implementation(libs.koin.android)
  implementation(libs.moshi)
  implementation(libs.moshi.adapters)
  implementation(libs.moshi.kotlin)
  implementation(libs.timber)
  implementation(libs.workManager)

  testImplementation(libs.junit)

  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.ui.test.junit4)
  androidTestImplementation(libs.mockwebserver)

  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.ui.test.manifest)
}