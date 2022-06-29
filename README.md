The goal of this project is showing a modern approach with using the latest technology stack of Android

### 💫 &nbsp; Powered by [TheMovieDb](https://www.themoviedb.org)

![alt text](https://cdn-images-1.medium.com/max/1200/1*vIR7iO-1GnY2xYxL6NiYkw.png)

## ⚙️ Configuration

In order to use MovieDB:
- You need to get API KEY from TMDb. You can do that by clicking [here](https://www.themoviedb.org/signup).
- Once you obtain key, create `secure.properties` which should be on the same level with build.gradle(Project:MovieDb)
- Add your key into secure.properties `MOVIE_API_KEY = "xxx" `
- Use JDK 11 to build this project.

## 🌞 Light Mode

<img src="/art/movie_list_light.png" width="250" /> &nbsp;&nbsp;&nbsp; <img src="/art/movie_detail_light.png" width="250" /> &nbsp;&nbsp;&nbsp; <img src="/art/search_list_light.png" width="250" />

## 🌚 Dark Mode

<img src="/art/movie_list_dark.png" width="250" /> &nbsp;&nbsp;&nbsp; <img src="/art/movie_detail_dark.png" width="250" /> &nbsp;&nbsp;&nbsp; <img src="/art/search_list_dark.png" width="250" />

## 📦 Modules

Modules are the collection of source files and build settings that allow you to divide your project into discrete units of functionality.

- **App Module**

  `:app` module is an [com.android.application](https://developer.android.com/studio/projects/android-library), which is needed to create the app bundle. It presents data to a screen and handle user interactions.

- **Core Module**

  `:core` module is an [com.android.library](https://developer.android.com/studio/projects/android-library), contains base classes and resources which are shared between other modules

- **Data Module**

  `:data` module is an [com.android.library](https://developer.android.com/studio/projects/android-library), contains models, api services and repository classes

- **Domain Module**

  `:domain` module is an [com.android.library](https://developer.android.com/studio/projects/android-library), contains use case and mapper classes
  
- **Presentation Module**

  `:presentation` module is an [com.android.library](https://developer.android.com/studio/projects/android-library), contains business logic and adapter classes
  
- **buildSrc**

  `:buildSrc` is a directory at the project root level which contains build info. It is used for dependency management for better reusability and easy maintenance

## 🛠 Built With

- [Kotlin](https://kotlinlang.org)
- [Jetpack](https://developer.android.com/jetpack?gclid=CjwKCAiA25v_BRBNEiwAZb4-ZRLrSzIFlpm0NDTFGSuapyosjuVKi0AVLXGgVqSwqe46gejCg31LvRoCAwIQAvD_BwE&gclsrc=aw.ds)
    * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)
    * [Data Binding](https://developer.android.com/topic/libraries/data-binding)
      - [Binding Adapter](https://developer.android.com/topic/libraries/data-binding/binding-adapters)
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
    * [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)
      - [Safe Args](https://developer.android.com/guide/navigation/navigation-pass-data)
    * [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
    * [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager)
    * [View Binding](https://developer.android.com/topic/libraries/view-binding)
    * [DataStore](https://developer.android.com/topic/libraries/architecture/datastore)
    * [Viewpager 2](https://developer.android.com/jetpack/androidx/releases/viewpager2)
    * [App Startup](https://developer.android.com/topic/libraries/app-startup)
- [Coroutines Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html)
  - [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)
- [Dagger Hilt](https://dagger.dev/hilt/)
- [Splash Screen API](https://developer.android.com/guide/topics/ui/splash-screen/migrate)
- [Retrofit](https://square.github.io/retrofit/)
- [Coil](https://github.com/coil-kt/coil)
- [FlexBox](https://github.com/google/flexbox-layout)
- [Flipper](https://github.com/facebook/flipper)
- [Testing](https://developer.android.com/training/testing/fundamentals)
  - [MockK](https://github.com/mockk/mockk)
  - [Kakao](https://github.com/KakaoCup/Kakao)
  - [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver)
  - [Turbine](https://github.com/cashapp/turbine)
  - [Truth](https://github.com/google/truth)
  - [Dagger Hilt Testing](https://developer.android.com/training/dependency-injection/hilt-testing)

## 🗼 Architecture

- [Model-View-ViewModel](https://en.wikipedia.org/wiki/Model–view–viewmodel)(MVVM) pattern helps to completely separate the business and presentation logic from the UI
- `Modular app architecture` enables separate functionality into independent, interchangeable modules
- [SOLID](https://en.wikipedia.org/wiki/SOLID) principles intended to make software designs more understandable, flexible, and maintainable

## 💎 Code style

To maintain the style and quality of the code

| Tool                                               |    Check command          |     Fix command
|----------------------------------------------------|---------------------------|---------------------------|
| [ktlint](https://github.com/diffplug/spotless)     | `./gradlew ktlintCheck`   | `./gradlew ktlintFormat`  |

## ✅ Version Control

[Gradle Versions Plugin](https://github.com/ben-manes/gradle-versions-plugin) for checking all dependencies for their current versions.

Run the `./gradlew dependencyUpdates` task. The plugin will create a file named `dependency_report.json` under `build/reports/dependencyUpdates` directory after completed successfully.
