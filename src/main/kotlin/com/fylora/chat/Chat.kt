package com.fylora.chat

import com.fylora.chat.model.ChatUser
import com.fylora.chat.model.Message
import com.fylora.core.user.User
import io.ktor.websocket.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
class Chat {

    private val users = emptyList<ChatUser>()

    fun userJoin(user: User, session: WebSocketSession): ChatUser {
        val id = user.id.toString()
        return ChatUser(
            userId = id,
            session = session,
            awaitingMessages = users.find { id == it.userId }?.awaitingMessages ?: mutableListOf()
        )
    }

    fun broadcast(message: Message) {
        GlobalScope.launch {
            users.forEach { user ->
                if(user.session == null) {
                    user.awaitingMessages.add(message)
                    return@launch
                }
            }
        }
    }

    fun send(message: Message) {
        TODO("Not yet implemented")
    }
}