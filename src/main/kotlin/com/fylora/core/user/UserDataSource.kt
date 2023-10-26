package com.fylora.core.user

interface UserDataSource {
    suspend fun getUserByUsername(username: String): User?
    suspend fun getUserById(id: String): User?
    suspend fun insertUser(user: User): Boolean
}