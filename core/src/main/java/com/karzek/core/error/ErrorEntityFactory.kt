package com.karzek.core.error

interface ErrorEntityFactory {
  fun toError(throwable: Throwable): ErrorEntity
}

interface ModuleErrorEntityFactory {
  fun toError(throwable: Throwable): ErrorEntity?
}
