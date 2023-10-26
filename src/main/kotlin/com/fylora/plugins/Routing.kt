package com.fylora.plugins

import com.fylora.auth.authenticate
import com.fylora.auth.getUserInfo
import com.fylora.auth.security.hashing.HashingService
import com.fylora.auth.security.token.TokenConfig
import com.fylora.auth.security.token.TokenService
import com.fylora.auth.signIn
import com.fylora.auth.signUp
import com.fylora.chat.chat
import com.fylora.core.user.UserDataSource
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(
    userDataSource: UserDataSource,
    hashingService: HashingService,
    tokenService: TokenService,
    tokenConfig: TokenConfig
) {
    routing {
        get("/") {
            call.respondText("dayum")
        }
        signIn(userDataSource, hashingService, tokenService, tokenConfig)
        signUp(hashingService, userDataSource)
        authenticate()
        getUserInfo()
        chat()
    }
}
