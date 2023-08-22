package com.lijewski.sample.domain.model

data class RegisterRequest(
    val email: String,
    val name: String,
    val surname: String,
    val password: String
)
