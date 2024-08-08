package com.karzek.core.network

import com.karzek.core.error.FeatureError

data class NetworkConnectionError(override val throwable: Throwable) : FeatureError(throwable)
data class UnauthorizedError(override val throwable: Throwable) : FeatureError(throwable)