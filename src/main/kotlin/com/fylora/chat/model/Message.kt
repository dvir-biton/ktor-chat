package com.fylora.chat.model

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val author: String,
    val authorId: String,
    val body: String,
    val timeStamp: Long
)
