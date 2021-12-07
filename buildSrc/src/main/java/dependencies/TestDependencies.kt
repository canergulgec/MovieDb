package dependencies

object TestDependencies {
    val uiTestLibraries = arrayListOf<String>().apply {
        add(Dependencies.espressoCore)
        add(Dependencies.espressoContrib)
        add(Dependencies.kakao)
        add(Dependencies.mockWebServer)
        add(Dependencies.fragmentTesting)
        add(Dependencies.workManagerTesting)
        add(Dependencies.daggerHiltTesting)
        add(Dependencies.navigationTesting)
    }

    val unitTestLibraries = arrayListOf<String>().apply {
        add(Dependencies.junit4)
        add(Dependencies.mockK)
        add(Dependencies.turbine)
        add(Dependencies.archTesting)
        add(Dependencies.coroutinesTest)
    }
}
