package com.lijewski.sample.domain.repository

import com.lijewski.sample.domain.model.LoginRequest
import com.lijewski.sample.domain.model.RegisterRequest
import com.lijewski.sample.data.model.AppUser

interface AuthRepository {

    val isLoggedIn: Result<Boolean>
    suspend fun login(loginRequest: LoginRequest): Result<Unit>
    suspend fun register(registerRequest: RegisterRequest): Result<Unit>
    fun getLoggedInUser(): Result<AppUser?>

    suspend fun updateEmail(newEmail: String, password: String): Result<Unit>
    suspend fun updatePassword(oldPassword: String, newPassword: String): Result<Unit>
}
