package com.lijewski.sample.domain.usecase

import com.lijewski.sample.domain.model.RegisterRequest
import com.lijewski.sample.common.EmptyEmailException
import com.lijewski.sample.common.EmptyNameException
import com.lijewski.sample.common.EmptyPasswordException
import com.lijewski.sample.common.InvalidEmailFormatException
import com.lijewski.sample.common.InvalidPasswordFormatException
import com.lijewski.sample.common.PASSWORD_MIN_LENGTH
import com.lijewski.sample.common.PasswordNotMatchException
import com.lijewski.sample.common.isValidEmail
import com.lijewski.sample.di.IoDispatcher
import com.lijewski.sample.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(
        name: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ): Result<Unit> {
        if (name.isBlank()) {
            return Result.failure(EmptyNameException())
        }
        if (email.isBlank()) {
            return Result.failure(EmptyEmailException())
        }
        if (!email.isValidEmail()) {
            return Result.failure(InvalidEmailFormatException())
        }
        if (password.length < PASSWORD_MIN_LENGTH) {
            return Result.failure(InvalidPasswordFormatException())
        }
        if (password.isBlank()) {
            return Result.failure(EmptyPasswordException())
        }
        if (password != passwordConfirmation) {
            return Result.failure(PasswordNotMatchException())
        }

        val registerRequest = RegisterRequest(
            email = email,
            password = password,
            name = name,
            surname = name,
        )

        return withContext(ioDispatcher) {
            repository.register(registerRequest)
        }
    }
}
