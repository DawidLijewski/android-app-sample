package com.lijewski.sample.data.repository

import com.lijewski.sample.data.model.TextMessage
import com.lijewski.sample.data.room.TextMessageDao
import com.lijewski.sample.data.room.TextMessageEntity
import com.lijewski.sample.data.room.convertToDats
import com.lijewski.sample.domain.repository.AuthRepository
import com.lijewski.sample.domain.repository.MessageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import javax.inject.Inject

class LocalMessageRepository @Inject constructor(
    private val textMessageDao: TextMessageDao,
    private val authRepository: AuthRepository,
) : MessageRepository {
    override fun getAllTextMessages(): Flow<List<TextMessage>> {
        return textMessageDao.readAllMessages().map { listMessages ->
            listMessages.map { message ->
                message.convertToDats()
            }
        }
    }

    override suspend fun saveTextMessage(newText: String): Result<Unit> {
        return runCatching {
            val textMessageEntity = TextMessageEntity(
                name = authRepository.getLoggedInUser().getOrNull()?.name ?: "User",
                text = newText,
                dateCreated = LocalDateTime.now().toString()
            )
            textMessageDao.addMessage(textMessageEntity = textMessageEntity)
        }
    }
}
