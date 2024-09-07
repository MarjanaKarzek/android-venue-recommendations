package com.karzek.venues.util

import androidx.test.platform.app.InstrumentationRegistry
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.nio.charset.StandardCharsets

const val MOCK_SERVER_PORT = 8283

fun readJsonFile(file: String): String =
  InstrumentationRegistry.getInstrumentation()
    .context
    .assets
    .open(file)
    .use {
      it.readBytes().toString(StandardCharsets.UTF_8)
    }

fun createDispatcher(endPointsMap: Map<String, String>): Dispatcher = object : Dispatcher() {
  @Throws(InterruptedException::class)
  override fun dispatch(request: RecordedRequest): MockResponse =
    parseResponse(request.path, endPointsMap) ?: MockResponse().setResponseCode(500)
}

private fun parseResponse(
  requestedPath: String?,
  endPointsMap: Map<String, String>
): MockResponse? =
  endPointsMap.keys
    .find { path -> requestedPath?.contains(path) ?: false }
    .let { endPointPath ->
      endPointsMap[endPointPath]?.let { response ->
        MockResponse().setResponseCode(200).setBody(response)
      }
    }