package com.lijewski.sample.presentation.uievents

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun UiEventsLaunchedEffects(
    navController: NavHostController,
    viewModel: UiEventViewModel = hiltViewModel(),
) {
    val dialogUiState by viewModel.uiEventsUiState.collectAsState()

    UiEventsLaunchedEffectsUI(
        eventFlow = viewModel.eventFlow,
        navController = navController,
        dialogUiState = dialogUiState,
        onDismiss = { viewModel.onDismiss() }
    )
}
@Composable
fun UiEventsLaunchedEffectsUI(
    eventFlow: Flow<UiEvent>,
    navController: NavHostController,
    dialogUiState: DialogUiState,
    onDismiss: () -> Unit,
) {
    LaunchedEffect(key1 = true) {
        eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.NavigateEvent -> {
                    navController.navigate(event.route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
                else -> {
                    //do nothing, handled in UiEventViewModel
                }
            }
        }
    }

    if (dialogUiState.isDisplayed) {
        when (dialogUiState.dialogType) {
            DialogType.ERROR -> {
                //ErrorDialog(descStringRes = dialogUiState.descStringRes, onDismiss = onDismiss)
            }

            DialogType.MODAL -> {
               /* ModalDialog(
                    titleStringRes = dialogUiState.titleStringRes,
                    descStringRes = dialogUiState.descStringRes,
                    onAccept = dialogUiState.onAcceptAction,
                    onDismiss = onDismiss,
                )*/
            }

            DialogType.DEVICE_NOT_CONNECTED -> {
                //DeviceNotConnectedAlertDialog(onDismiss = onDismiss)
            }

            null -> {
                println("DialogType is null!")
            }
        }
    }
}
