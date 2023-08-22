package com.lijewski.sample.data.repository

import com.lijewski.sample.data.model.AppUser
import com.lijewski.sample.domain.model.LoginRequest
import com.lijewski.sample.domain.model.RegisterRequest
import com.lijewski.sample.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MockAuthRepository @Inject constructor(): AuthRepository {
    override val isLoggedIn: Result<Boolean>
        get() = TODO("Not yet implemented")

    override suspend fun login(loginRequest: LoginRequest): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun register(registerRequest: RegisterRequest): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun getLoggedInUser(): Result<AppUser?> {
        //TODO: replace with reading from sharedpref/room
        return Result.success(AppUser(id = "id1313", email = "test@test.com", name = "Test User"))
    }

    override suspend fun updateEmail(newEmail: String, password: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun updatePassword(oldPassword: String, newPassword: String): Result<Unit> {
        TODO("Not yet implemented")
    }
}
