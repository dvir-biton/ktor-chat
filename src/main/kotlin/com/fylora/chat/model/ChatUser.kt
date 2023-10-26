package com.fylora.chat.model

import com.fylora.core.user.User
import io.ktor.websocket.*
import kotlinx.coroutines.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

data class ChatUser(
    val userId: String,
    val session: WebSocketSession? = null,
    val awaitingMessages: MutableList<Message> = mutableListOf()
) {
    private var pingJob: Job? = null

    private var pingTime = 0L
    private var pongTime = 0L

    var isOnline = true

    @OptIn(DelicateCoroutinesApi::class)
    fun sendAwaitingMessages() {
        if(session == null) {
            return
        }

        GlobalScope.launch {
            awaitingMessages.forEach { message ->
                session.send(
                    Json.encodeToString(message)
                )
                awaitingMessages.remove(message)
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun startPing() {
        pingJob?.cancel()

        pingJob = GlobalScope.launch {
            while(true) {
                ping()
                delay(PING_FREQUENCY)
            }
        }
    }

    private suspend fun ping() {
        pingTime = System.currentTimeMillis()
        this.session?.send(Json.encodeToString(Ping))
        delay(PING_FREQUENCY)

        if(pingTime - pongTime > PING_FREQUENCY) {
            isOnline = false
            pingJob?.cancel()
        }
    }

    companion object {
        const val PING_FREQUENCY = 3000L
    }
}
