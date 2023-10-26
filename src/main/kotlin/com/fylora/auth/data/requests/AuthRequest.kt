package com.fylora.auth.data.requests

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(
    val username: String,
    val password: String
)
