plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.jetbrains.kotlin.android)
}
android {
  namespace = "com.karzek.core"
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
  debugImplementation(libs.chucker)
  releaseImplementation(libs.chucker.no.op)
}