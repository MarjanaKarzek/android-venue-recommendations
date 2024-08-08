package com.karzek.core.error

sealed class ErrorEntity(open val throwable: Throwable)

data class UnknownError(override val throwable: Throwable) : ErrorEntity(throwable)

//extend this class for feature specific errors
abstract class FeatureError(override val throwable: Throwable) : ErrorEntity(throwable)