package com.karzek.core.result

import com.karzek.core.error.ErrorEntityFactory
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

suspend fun <T : Any> safeApiCall(
  coroutineContext: CoroutineContext,
  errorFactory: ErrorEntityFactory,
  apiCall: suspend () -> T,
): Result<T> = withContext(coroutineContext) {
  try {
    Result.Success(apiCall.invoke())
  } catch (throwable: Throwable) {
    Result.Error(errorFactory.toError(throwable))
  }
}
