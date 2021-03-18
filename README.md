The goal of this project is showing a modern approach with using the latest technology stack of Android

:dizzy: Powered by [TheMovieDb](https://www.themoviedb.org)
 
![alt text](https://cdn-images-1.medium.com/max/1200/1*vIR7iO-1GnY2xYxL6NiYkw.png)

### Configuration

In order to use MovieDB:
- You need to get API KEY from TMDb. You can do that by clicking [here](https://www.themoviedb.org/signup).
- Once you obtain key, create `secure.properties` which should be on the same level with build.gradle(Project:MovieDb)
- Add your key into secure.properties `MOVIE_API_KEY = "xxx" `

### Modules

Modules are the collection of source files and build settings that allow you to divide your project into discrete units of functionality.

- **App Module**

  `:app` module is an [com.android.application](https://developer.android.com/studio/projects/android-library), which is needed to create the app bundle. It presents data to a screen and handle user interactions.

- **Base Module**

  `:base` module is an [com.android.library](https://developer.android.com/studio/projects/android-library), contains only base classes that is used in other modules

- **Common Module**

  `:common` module is an [com.android.library](https://developer.android.com/studio/projects/android-library), contains code and resources which are shared between other modules

- **Data Module**
  
  `:data` module is an [com.android.library](https://developer.android.com/studio/projects/android-library), contains models and utility classes such as [DiffUtil](https://developer.android.com/reference/androidx/recyclerview/widget/DiffUtil)

- **Domain Module**

  `:domain` module is an [com.android.library](https://developer.android.com/studio/projects/android-library), that retrieves data from network calls and provides data source for the many features that requires it.

- **Presentation Module**

  `:presentation` module is an [com.android.library](https://developer.android.com/studio/projects/android-library), contains business logic and adapter classes

### Dependencies

- [Kotlin](https://kotlinlang.org)
- [Jetpack](https://developer.android.com/jetpack?gclid=CjwKCAiA25v_BRBNEiwAZb4-ZRLrSzIFlpm0NDTFGSuapyosjuVKi0AVLXGgVqSwqe46gejCg31LvRoCAwIQAvD_BwE&gclsrc=aw.ds)
    * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)
    * [Data Binding](https://developer.android.com/topic/libraries/data-binding)
      - [Binding Adapter](https://developer.android.com/topic/libraries/data-binding/binding-adapters)
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
    * [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
    * [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager)
    * [View Binding](https://developer.android.com/topic/libraries/view-binding)
    * [DataStore](https://developer.android.com/topic/libraries/architecture/datastore)
    * [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)
- [Coroutines Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html)
  - [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)
- [Dagger Hilt](https://dagger.dev/hilt/)
- [Viewpager 2](https://developer.android.com/jetpack/androidx/releases/viewpager2)
- [Retrofit](https://square.github.io/retrofit/)
- [Coil](https://github.com/coil-kt/coil)
- [FlexBox](https://github.com/google/flexbox-layout)
- [Mockito-Kotlin](https://github.com/mockito/mockito-kotlin)
- [MockK](https://github.com/mockk/mockk)
- [Kakao](https://github.com/agoda-com/Kakao)
- [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver)
- [Flipper](https://github.com/facebook/flipper)

### Architecture

- [Model-View-ViewModel](https://en.wikipedia.org/wiki/Model–view–viewmodel)(MVVM) pattern helps to completely separate the business and presentation logic from the UI
- `Modular app architecture` enables separate functionality into independent, interchangeable modules
- [SOLID](https://en.wikipedia.org/wiki/SOLID) principles intended to make software designs more understandable, flexible, and maintainable

### Code style

To maintain the style and quality of the code

| Tool                                                   |    Check command          |     Fix command               
|--------------------------------------------------------|---------------------------|--------------------------- |
| [spotless](https://github.com/diffplug/spotless)       | `./gradlew spotlessCheck` | `./gradlew spotlessApply`  |
| [ktlint](https://github.com/pinterest/ktlint)          | `./gradlew ktlintCheck`   | `./gradlew ktlintApply`    |

### Screenshots

<img src="/art/home_screen.png" width="300" /> &nbsp;&nbsp;&nbsp; <img src="/art/movie_detail.png" width="300" /> &nbsp;&nbsp;&nbsp; <img src="/art/movie_search.png" width="300" />
