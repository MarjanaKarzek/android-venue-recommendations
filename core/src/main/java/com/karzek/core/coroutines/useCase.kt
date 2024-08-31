package com.karzek.core.coroutines

interface SuspendOutUseCase<out Output> {
  suspend fun execute(): Output
}