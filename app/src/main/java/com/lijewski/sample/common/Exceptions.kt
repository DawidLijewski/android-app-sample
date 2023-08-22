package com.lijewski.sample.common

class EmptyNameException : Exception("Name cannot be blank")
class EmptyEmailException : Exception("E-mail cannot be blank")
class InvalidEmailFormatException : Exception("E-mail address is invalid format")
class EmptyPasswordException : Exception("Password cannot be blank")
class PasswordNotMatchException : Exception("Password not match")
class PasswordNotUpdatedException : Exception("Password not updated")
class InvalidPasswordFormatException : Exception("Password is invalid format")
class NewPasswordSameAsOldException: Exception("New password can't be the same as old")
class EmailNotUpdatedException : Exception("Email not updated")
class UserNotFoundException : Exception("User not found")
class RegisterUserAlreadyExistsException : Exception("User already exists")
class InvalidCredentialsException : Exception("Invalid credentials")
