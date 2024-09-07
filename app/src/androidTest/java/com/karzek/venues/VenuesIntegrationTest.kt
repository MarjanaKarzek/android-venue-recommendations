package com.karzek.venues

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.karzek.venues.ui.MainActivity
import com.karzek.venues.util.MOCK_SERVER_PORT
import com.karzek.venues.util.createDispatcher
import com.karzek.venues.util.readJsonFile
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class VenuesIntegrationTest {

  @Rule(order = 1)
  @JvmField
  var testRule = ActivityScenarioRule(MainActivity::class.java)

  @get:Rule(order = 0)
  val composeTestRule = createEmptyComposeRule()

  private val server = MockWebServer()

  private val dispatcherSuccess: Dispatcher = createDispatcher(
    mapOf("v1/pages/restaurants" to readJsonFile("restaurants_response.json"))
  )

  @Before
  fun setUp() {
    server.start(MOCK_SERVER_PORT)
  }

  @After
  fun tearDown() {
    server.shutdown()
  }

  @Test
  fun when_dataSuccess_then_successState() {
    server.dispatcher = dispatcherSuccess
    with(composeTestRule) {
      waitUntilExactlyOneExists(hasText("McDonald's Helsinki Kamppi"), 5_000L)
      onNode(hasText("I'm lovin' it.")).assertIsDisplayed()
    }
  }
}