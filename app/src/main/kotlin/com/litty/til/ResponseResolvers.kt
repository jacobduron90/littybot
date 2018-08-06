package com.litty.til

import com.google.gson.Gson
import com.litty.til.models.SlackEventRequest
import com.litty.til.models.SlackVerificationResponse
import io.javalin.Context

class ResponseResolvers(val gson: Gson) {


    fun onUrlVerification(authRequest: SlackEventRequest, context: Context) {
        val verificationResponse = SlackVerificationResponse(challenge = authRequest.challenge)
        context.status(200)
        context.result(gson.toJson(verificationResponse))
    }

    fun onNewMessage(messageRequest: SlackEventRequest, context: Context) {
        val message = messageRequest.event.text!!
        println("got message from slack: ${message}")
        val channel = messageRequest.event.channel!!
        println("got channel from slack: ${channel}")
        val token = messageRequest.token
        println("got token from slack: ${token}")
        sendMessageToSlack(
                message = "Yo, whassup. I saw your message ${message}",
                room = channel
        )

        context.status(200)
        context.result("message received")
    }

    fun onReactionAdded(authRequest: SlackEventRequest, context: Context) {

    }


}