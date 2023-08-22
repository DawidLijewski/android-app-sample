package com.lijewski.sample.presentation.bottomnav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null,
)

class PhotosNavItem : BottomNavigationItem(
    title = "Gallery",
    selectedIcon = Icons.Filled.List,
    unselectedIcon = Icons.Outlined.List,
)

class MessagesNavItem : BottomNavigationItem(
    title = "Messages",
    selectedIcon = Icons.Filled.Email,
    unselectedIcon = Icons.Outlined.Email,
)

class SettingsNavItem : BottomNavigationItem(
    title = "Settings",
    selectedIcon = Icons.Filled.Settings,
    unselectedIcon = Icons.Outlined.Settings,
)
