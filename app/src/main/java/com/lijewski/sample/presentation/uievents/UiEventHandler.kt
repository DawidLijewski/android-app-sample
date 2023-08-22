package com.lijewski.sample.presentation.uievents

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UiEventHandler @Inject constructor() {
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    suspend fun emitEvent(uiEvent: UiEvent) {
        _eventFlow.emit(uiEvent)
    }
}
