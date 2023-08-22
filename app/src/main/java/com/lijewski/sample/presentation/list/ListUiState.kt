package com.lijewski.sample.presentation.list

import com.lijewski.sample.data.model.TextMessage

data class ListUiState(
    val isLoading: Boolean = false,
    val messagesList: List<TextMessageUiState> = listOf(),
    val isNewMessageDialogVisible: Boolean = false,
)

data class TextMessageUiState(
    val data: TextMessage,
    val isSelected: Boolean = false,
)
