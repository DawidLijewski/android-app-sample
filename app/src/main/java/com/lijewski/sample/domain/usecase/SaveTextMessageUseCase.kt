package com.lijewski.sample.domain.usecase

import com.lijewski.sample.di.IoDispatcher
import com.lijewski.sample.domain.repository.MessageRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveTextMessageUseCase @Inject constructor(
    private val repository: MessageRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(newText: String): Result<Unit> {
        return withContext(ioDispatcher) {
            repository.saveTextMessage(newText)
        }
    }
}
