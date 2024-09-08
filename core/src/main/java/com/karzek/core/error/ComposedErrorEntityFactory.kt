package com.karzek.core.error

class ComposedErrorEntityFactory(
  private val factories: List<FeatureErrorEntityFactory> = emptyList(),
) : ErrorEntityFactory {
  override fun toError(throwable: Throwable): ErrorEntity {
    factories.forEach { factory ->
      val errorEntity = factory.toError(throwable)
      errorEntity?.let { return it }
    }
    return UnknownError(throwable)
  }
}
