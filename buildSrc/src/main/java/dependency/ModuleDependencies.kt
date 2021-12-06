@file:Suppress("PackageDirectoryMismatch")

object ModuleDependencies {
    val coreModuleLibraries = arrayListOf<String>().apply {
        add(Dependencies.appCompat)
        add(Dependencies.lifecycleLiveData)
        add(Dependencies.fragmentKtx)
        add(Dependencies.coreKtx)
        add(Dependencies.daggerHilt)
        add(Dependencies.coroutinesCore)
        add(Dependencies.cardView)
        add(Dependencies.paging)
        add(Dependencies.coil)
    }

    val dataModuleLibraries = arrayListOf<String>().apply {
        add(Dependencies.appCompat)
        add(Dependencies.coreKtx)
        add(Dependencies.daggerHilt)
        add(Dependencies.gsonConverter)
        add(Dependencies.recyclerView)
        add(Dependencies.dataStore)
    }

    val domainModuleLibraries = arrayListOf<String>().apply {
        add(Dependencies.appCompat)
        add(Dependencies.coreKtx)
        add(Dependencies.lifecycleLiveData)
        add(Dependencies.fragmentKtx)
        add(Dependencies.retrofit)
        add(Dependencies.gsonConverter)
        add(Dependencies.okHttp)
        add(Dependencies.okHttpInterceptor)
        add(Dependencies.daggerHilt)
        add(Dependencies.stetho)
        add(Dependencies.stethoOkHttp)
        add(Dependencies.dataStore)
        add(Dependencies.paging)
    }

    val presentationModuleLibraries = arrayListOf<String>().apply {
        add(Dependencies.appCompat)
        add(Dependencies.material)
        add(Dependencies.constraintLayout)
        add(Dependencies.lifecycleLiveData)
        add(Dependencies.coreKtx)
        add(Dependencies.daggerHilt)
        add(Dependencies.paging)
        add(Dependencies.viewPager)
        add(Dependencies.hiltWorkManager)
        add(Dependencies.dataStore)
        add(Dependencies.timber)
        add(Dependencies.startup)
    }
}