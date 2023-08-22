package com.lijewski.sample.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lijewski.sample.presentation.bottomnav.BottomNavigationItem
import com.lijewski.sample.presentation.bottomnav.MessagesNavItem
import com.lijewski.sample.presentation.bottomnav.PhotosNavItem
import com.lijewski.sample.presentation.bottomnav.SettingsNavItem
import com.lijewski.sample.presentation.destinations.ListScreenDestination
import com.lijewski.sample.presentation.destinations.PhotosScreenDestination
import com.lijewski.sample.presentation.destinations.SettingsScreenDestination
import com.lijewski.sample.presentation.uievents.UiEvent
import com.lijewski.sample.presentation.uievents.UiEventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val uiEventHandler: UiEventHandler,
) : ViewModel() {
    val navItemList = listOf(
        PhotosNavItem(),
        MessagesNavItem(),
        SettingsNavItem(),
    )

    fun handleNavDestinationTopBar(bottomNavigationItem: BottomNavigationItem) {
        viewModelScope.launch {
            val destinationRoute = when(bottomNavigationItem) {
                is PhotosNavItem -> PhotosScreenDestination.route
                is MessagesNavItem -> ListScreenDestination.route
                is SettingsNavItem -> SettingsScreenDestination.route
            }
            uiEventHandler.emitEvent(UiEvent.NavigateEvent(destinationRoute))
        }
    }
}
