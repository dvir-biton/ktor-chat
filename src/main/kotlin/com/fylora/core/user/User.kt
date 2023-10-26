package com.fylora.core.user

import io.ktor.server.auth.*
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class User(
    val username: String,
    val password: String,
    val salt: String,

    val contacts: List<ObjectId> = emptyList(),

    @BsonId
    val id: ObjectId = ObjectId()
): Principal
