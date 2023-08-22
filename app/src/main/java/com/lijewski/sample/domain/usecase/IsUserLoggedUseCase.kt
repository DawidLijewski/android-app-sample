package com.lijewski.sample.domain.usecase

import com.lijewski.sample.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IsUserLoggedUseCase @Inject constructor(
    private val repository: AuthRepository,
) {
    operator fun invoke(): Result<Boolean> {
        return repository.isLoggedIn
    }
}
