package com.lijewski.sample.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lijewski.sample.R
import com.lijewski.sample.domain.usecase.GetTextMessagesUseCase
import com.lijewski.sample.domain.usecase.SaveTextMessageUseCase
import com.lijewski.sample.presentation.uievents.UiEvent
import com.lijewski.sample.presentation.uievents.UiEventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getTextMessagesUseCase: GetTextMessagesUseCase,
    private val saveTextMessageUseCase: SaveTextMessageUseCase,
    private val uiEventHandler: UiEventHandler,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ListUiState())
    val uiState: StateFlow<ListUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getTextMessagesUseCase.invoke().collect { listTextMessages ->
                _uiState.update {
                    it.copy(messagesList = listTextMessages.map { textMessage ->
                        TextMessageUiState(data = textMessage)
                    })
                }
            }
        }
    }

    fun onTextMessageItemClicked(itemIndex: Int) {
        val mutableMessageList = _uiState.value.messagesList.toMutableList()
        mutableMessageList[itemIndex] =
            mutableMessageList[itemIndex].copy(isSelected = !mutableMessageList[itemIndex].isSelected)
        _uiState.update {
            it.copy(
                messagesList = mutableMessageList
            )
        }
    }

    fun onAddNewTextMessage() {
        _uiState.update { it.copy(isNewMessageDialogVisible = true) }
    }

    fun saveNewTextMessage(newText: String) {
        viewModelScope.launch {
            saveTextMessageUseCase.invoke(newText).fold(
                onSuccess = { _uiState.update { it.copy(isNewMessageDialogVisible = false) } },
                onFailure = { uiEventHandler.emitEvent(UiEvent.ErrorDialog(R.string.error_saving_new_msg)) }
            )
        }
    }
}
