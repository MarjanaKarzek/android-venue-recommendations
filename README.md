# android-venue-recommendations
App that shows venue recommendations for mocked location updates.

## architecture
The app is following the clean architecture principles dividing the layers into ui, domain and data.
Within the data layer the repository pattern is applied. The app is modularized and in a more complex
application the ui for the restaurants would have been moved out of the app module.

## tech stack
Kotlin
Koin
Compose
MVVM
Flows
SharedPreferences
Moshi
Retrofit
OkHttp
Timber

Paparazzi
JUnit4 & JUnit5
MockitoKotlin
ComposeUI Testing

## Shortcuts
Since the UI is consisting only of one screen I decided against using a fragment or the Navigation framework.
Also, since the wishlist is basically a list of Strings I avoided using a complex data source management framework. 
I would have used Room or DataStore for more complex data.

Please let me know in case you would like to see me demonstrating usages of the above mentioned shortcuts.

For tests you can find an example of a Screenshot, Integration and Unit test. 
If the examples are not sufficient I can provide more.

