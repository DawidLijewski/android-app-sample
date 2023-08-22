package com.lijewski.sample.domain.usecase

import com.lijewski.sample.data.model.TextMessage
import com.lijewski.sample.di.IoDispatcher
import com.lijewski.sample.domain.repository.MessageRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetTextMessagesUseCase @Inject constructor(
    private val repository: MessageRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {
    operator fun invoke(): Flow<List<TextMessage>> {
        return repository.getAllTextMessages().flowOn(ioDispatcher)
    }
}
