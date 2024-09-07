# android-venue-recommendations
App that shows venue recommendations for mocked location updates.

## architecture
The app is following the clean architecture principles dividing the layers into ui, domain and data.
Within the data layer the repository pattern is applied. The app is modularized and in a more complex
application the ui for the restaurants would have been moved out of the app module.

## tech stack
Kotlin
Flows
Koin

Compose
MVVM
SharedPreferences
Moshi
Retrofit
OkHttp
Timber
WorkManager

Paparazzi
JUnit4 & JUnit5
MockitoKotlin
ComposeUI Testing
MockWebServer

## Sample Tests
For tests you can find an example of a Screenshot, Integration and Unit test.
If the examples are not sufficient I can provide more.

Screenshot: IconButtonScreenshotTest
Unit: RestaurantRepositoryImplTest
Integration: VenuesIntegrationTest

## Shortcuts
Since the UI is consisting only of one screen I decided against using a fragment or the Navigation framework.
Also, since the wishlist is basically a list of Strings I avoided using a complex data source management framework. 
I would have used Room or DataStore for more complex data.

Please let me know in case you would like to see me demonstrating usages of the above mentioned shortcuts.

In order to demonstrate how I would implement background work I used an empty Worker as I think its enough
to showcase how I would implement it. In case you want me to implement more of the logic, let me know please.

For the restaurant endpoint I tried to find a query parameter that would limit the restaurant 
response but couldn't find any. In a production app I would send the limit to the backend rather than
filtering 15 items after all were received to respect the users data usage.

