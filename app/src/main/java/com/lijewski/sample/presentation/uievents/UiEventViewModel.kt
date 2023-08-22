package com.lijewski.sample.presentation.uievents

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class UiEventViewModel @Inject constructor(
    uiEventHandler: UiEventHandler,
) : ViewModel() {
    private val _uiEventsUiState = MutableStateFlow(DialogUiState())
    val uiEventsUiState: StateFlow<DialogUiState> = _uiEventsUiState.asStateFlow()

    val eventFlow = uiEventHandler.eventFlow.onEach {
        when (it) {
            is UiEvent.DeviceNotConnectedDialog -> {
                _uiEventsUiState.update { dialogUiState ->
                    dialogUiState.copy(
                        isDisplayed = true,
                        dialogType = DialogType.DEVICE_NOT_CONNECTED
                    )
                }
            }

            is UiEvent.ErrorDialog -> {
                _uiEventsUiState.update { dialogUiState ->
                    dialogUiState.copy(
                        isDisplayed = true,
                        dialogType = DialogType.ERROR,
                        descStringRes = it.descStringRes
                    )
                }
            }

            is UiEvent.ModalDialog -> {
                _uiEventsUiState.update { dialogUiState ->
                    dialogUiState.copy(
                        isDisplayed = true,
                        dialogType = DialogType.MODAL,
                        descStringRes = it.descStringRes,
                        titleStringRes = it.titleStringRes,
                        onAcceptAction = it.onAccept
                    )
                }
            }

            is UiEvent.NavigateEvent -> {
                _uiEventsUiState.update { dialogUiState ->
                    dialogUiState.copy(isDisplayed = false)
                }
            }
        }
    }

    fun onDismiss() {
        _uiEventsUiState.update { dialogUiState ->
            dialogUiState.copy(
                isDisplayed = false,
                dialogType = null,
                titleStringRes = null,
                descStringRes = null,
                onAcceptAction = {},
            )
        }
    }
}
