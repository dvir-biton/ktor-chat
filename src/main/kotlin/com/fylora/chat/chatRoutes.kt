package com.fylora.chat

import com.fylora.core.user.User
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*

fun Route.chat() {
    webSocket("/chat") {
        val user = call.authentication.principal<User>()

        if(user == null) {
            close(CloseReason(CloseReason.Codes.VIOLATED_POLICY, "Authentication required"))
            return@webSocket
        }
    }
}