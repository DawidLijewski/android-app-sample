package com.lijewski.sample.domain.usecase

import com.lijewski.sample.common.EmptyPasswordException
import com.lijewski.sample.common.InvalidPasswordFormatException
import com.lijewski.sample.common.NewPasswordSameAsOldException
import com.lijewski.sample.common.PASSWORD_MIN_LENGTH
import com.lijewski.sample.di.IoDispatcher
import com.lijewski.sample.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdatePasswordUseCase @Inject constructor(
    private val repository: AuthRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(oldPassword: String, newPassword: String): Result<Unit> {
        if (newPassword.length < PASSWORD_MIN_LENGTH) {
            return Result.failure(InvalidPasswordFormatException())
        }
        if (newPassword.isBlank()) {
            return Result.failure(EmptyPasswordException())
        }
        if (oldPassword == newPassword) {
            return Result.failure(NewPasswordSameAsOldException())
        }

        return withContext(ioDispatcher) {
            repository.updatePassword(
                oldPassword = oldPassword,
                newPassword = newPassword,
            )
        }
    }
}
