# Locations

You can download the APK [here][apk] or see the images [here][images].

## Description

Build a simple application that consumes a public location based rest service (Foursquare, Yelp, etc.) to query a list of businesses near you. Your application should display and sort those businesses based on distance from the device’s current location. Use the provided Photoshop document when building the user interface for displaying those businesses.

Additionally the application should use a caching mechanism to store your queried data so it can still be displayed if the device cannot access the internet.

As a bonus objective, we would love to see you display queried locations on a mapview, and a some integration/unit tests around your code.

Feel free to use any open source project dependencies you are comfortable with. Your code should be hosted on GitHub.

## Implementation Details

1. It use the concept of Package by Feature / Domain.
2. It use MVP pattern for presentation and to make tests easier.
3. It use Dagger for Dependency Injection.
4. It use Repository Pattern with OkHttp, Retrofit and Json for data loading.
5. It use RxJava with Retrolambda.
6. It use ButterKnife for view bind.
7. It use SVG.
8. It has some unit testing with JUnit and Mockito (see test folder).
9. It has some Ui tests with Espresso (see androidTest folder).
10. It use CustomView implementation with parameters by XML (see DatePickerView).
11. It use Hawk to cache (work offline).

##Important:

1. Unit and Ui tests **must run with Mock flavor**.
2. I've exposed the keys (see **gradle.properties** and **keystore/keystore.jks**) because it's a **sample project**.
3. I've used API 22 because of the permissions.
4. You need to **enable Location** to use this App.
5. **TODO:** Move strings from layout to Strings.xml.

## UX / UI

![Launch Screen][design-launch]
![Home Screen][design-home]
![Error Screen][design-error]
![Loading Screen][design-loading]

[apk]: https://github.com/marcellogalhardo/locations/releases/download/1.0.0/locations.apk
[images]: https://github.com/marcellogalhardo/locations/tree/master/images
[design-launch]: https://github.com/marcellogalhardo/locations/blob/master/images/launch.png
[design-home]: https://github.com/marcellogalhardo/locations/blob/master/images/home.png
[design-loading]: https://github.com/marcellogalhardo/locations/blob/master/images/loading.png
[design-error]: https://github.com/marcellogalhardo/locations/blob/master/images/error.png
