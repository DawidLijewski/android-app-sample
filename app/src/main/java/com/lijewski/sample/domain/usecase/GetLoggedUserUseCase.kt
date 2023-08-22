package com.lijewski.sample.domain.usecase

import com.lijewski.sample.data.model.AppUser
import com.lijewski.sample.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLoggedUserUseCase @Inject constructor(
    private val repository: AuthRepository,
) {
    operator fun invoke(): Result<AppUser?> {
        return repository.getLoggedInUser()
    }
}
