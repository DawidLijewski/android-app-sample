package com.lijewski.sample.presentation.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.lijewski.sample.presentation.NavGraphs
import com.lijewski.sample.presentation.bottomnav.BottomNavBar
import com.lijewski.sample.presentation.bottomnav.BottomNavigationItem
import com.lijewski.sample.presentation.uievents.UiEventsLaunchedEffects
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.rememberNavHostEngine

@Destination
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
) {
    val navItems = viewModel.navItemList //TODO: refactor into uiStates & MutableStateFlow

    MainScreenUI(
        navItems = navItems,
        handleDestinationForBottomBar = { viewModel.handleNavDestinationTopBar(it) },
    )
}

@Composable
fun MainScreenUI(
    navItems: List<BottomNavigationItem>,
    handleDestinationForBottomBar: (BottomNavigationItem) -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val navController = rememberNavController()
    val navHostEngine = rememberNavHostEngine()

    UiEventsLaunchedEffects(
        navController = navController,
    )

    MainScaffold(
        snackbarHostState = snackbarHostState,
        bottomBar = {
            BottomNavBar(
                navItems = navItems,
                handleDestinationForBottomBar = handleDestinationForBottomBar,
            )
        },
    ) {
        DestinationsNavHost(
            engine = navHostEngine,
            navController = navController,
            navGraph = NavGraphs.root,
            modifier = Modifier.padding(it),
            startRoute = NavGraphs.root.startRoute
        )
    }
}

@Composable
fun MainScaffold(
    snackbarHostState: SnackbarHostState,
    bottomBar: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        bottomBar = bottomBar,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = content
    )
}
