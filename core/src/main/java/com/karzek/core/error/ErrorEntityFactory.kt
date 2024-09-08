package com.karzek.core.error

interface ErrorEntityFactory {
  fun toError(throwable: Throwable): ErrorEntity
}

interface FeatureErrorEntityFactory {
  fun toError(throwable: Throwable): ErrorEntity?
}
