package com.lijewski.sample.presentation.uievents

import androidx.annotation.StringRes

sealed class UiEvent {
    data class NavigateEvent(val route: String) : UiEvent()
    data class ErrorDialog(@StringRes val descStringRes: Int?) : UiEvent()
    data class ModalDialog(
        @StringRes val titleStringRes: Int,
        @StringRes val descStringRes: Int,
        val onAccept: () -> Unit,
    ) : UiEvent()
    data object DeviceNotConnectedDialog : UiEvent()
}
