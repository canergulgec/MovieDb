package com.caner.navigation

import androidx.navigation.NavDirections
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@ActivityRetainedScoped
class NavigationDispatcher @Inject constructor() {
    private val navigationEmitter = MutableSharedFlow<NavigationCommand>(replay = 0)
    val navigationCommands: SharedFlow<NavigationCommand> = navigationEmitter.asSharedFlow()

    private suspend fun emit(navigationCommand: NavigationCommand) {
        navigationEmitter.emit(navigationCommand)
    }

    suspend fun navigateTo(direction: NavDirections) {
        emit { navController ->
            navController.navigate(direction)
        }
    }
}
