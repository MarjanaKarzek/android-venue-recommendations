package com.karzek.core.network

import android.view.inputmethod.TextBoundsInfoResult.CODE_CANCELLED
import com.karzek.core.error.FeatureErrorEntityFactory
import okhttp3.internal.http2.ConnectionShutdownException
import okhttp3.internal.http2.StreamResetException
import retrofit2.HttpException
import java.io.IOException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.net.ssl.SSLException
import javax.net.ssl.SSLHandshakeException
import javax.net.ssl.SSLPeerUnverifiedException

object NetworkErrorEntityFactory : FeatureErrorEntityFactory {

  override fun toError(throwable: Throwable) = when {
    throwable.isBadConnection() -> NetworkConnectionError(throwable)
    throwable is HttpException -> mapAPIException(throwable)
    else -> null
  }

  private fun mapAPIException(throwable: HttpException) = when (throwable.code()) {
    HttpURLConnection.HTTP_UNAUTHORIZED -> UnauthorizedError(throwable)
    else -> null
  }
}

fun Throwable?.isBadConnection() = when {
  this is UnknownHostException -> true
  this is ConnectException -> true
  this is InterruptedException -> true
  this is InterruptedIOException -> true
  this is SocketTimeoutException -> true
  this is SSLPeerUnverifiedException -> true
  this is ConnectionShutdownException -> true
  this is StreamResetException -> true
  this is SocketException -> true
  this is SSLException -> true
  this is SSLHandshakeException -> true
  this is TimeoutException -> true
  this is IOException && message == "Unexpected response code for CONNECT: 504" -> true
  this is IOException && message?.contains("Timed out") ?: false -> true
  this is HttpException && this.code() == CODE_CANCELLED -> true
  else -> false
}

