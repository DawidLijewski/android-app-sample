package com.lijewski.sample.presentation.uievents

import androidx.annotation.StringRes

data class DialogUiState(
    val isDisplayed: Boolean = false,
    val dialogType: DialogType? = null,
    @StringRes val titleStringRes: Int? = null,
    @StringRes val descStringRes: Int? = null,
    val onAcceptAction: () -> Unit = {}
)
