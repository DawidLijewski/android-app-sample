package com.lijewski.sample.domain.repository

import com.lijewski.sample.data.model.TextMessage
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    fun getAllTextMessages(): Flow<List<TextMessage>>
    suspend fun saveTextMessage(newText: String): Result<Unit>
}
