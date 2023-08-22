package com.lijewski.sample.domain.usecase

import com.lijewski.sample.domain.model.LoginRequest
import com.lijewski.sample.common.EmptyEmailException
import com.lijewski.sample.common.EmptyPasswordException
import com.lijewski.sample.common.InvalidEmailFormatException
import com.lijewski.sample.common.isValidEmail
import com.lijewski.sample.di.IoDispatcher
import com.lijewski.sample.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginUseCase @Inject constructor(
    private val repository: AuthRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): Result<Unit> {
        if (email.isBlank()) {
            return Result.failure(EmptyEmailException())
        }
        if (!email.isValidEmail()) {
            return Result.failure(InvalidEmailFormatException())
        }
        if (password.isBlank()) {
            return Result.failure(EmptyPasswordException())
        }

        val loginRequest = LoginRequest(
            email = email,
            password = password
        )

        return withContext(ioDispatcher) {
            repository.login(loginRequest)
        }
    }
}
