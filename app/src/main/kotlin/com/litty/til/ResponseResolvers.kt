package com.litty.til

import com.google.gson.Gson
import com.litty.til.models.SlackEventRequest
import com.litty.til.models.SlackVerificationResponse
import io.javalin.Context

class ResponseResolvers(val gson: Gson, val firebaseClient: FirebaseClient) {


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


        if (importantMessage(message)) {
            val action = getAction(message)
            when (action) {
                CommandType.REGISTER -> register(message)
                else -> println("not supported yet")
            }
        }

        context.status(200)
        context.result("message received")
    }

    private fun unregister(message: String) {
        println("hit unregister method")
    }

    private fun register(message: String) {
        println("hit register method")
        val tag = getTag(message)
        println("got tag $tag")
    }

    fun getTag(message: String): String? {
        val spaceArray = message.split(" ")

        spaceArray.forEach {
            if(it.startsWith("tag=")) {
                return it.substring(3, it.length - 1)
            }
        }
        return null
    }



    enum class CommandType {
        REGISTER,
        UNREGISTER,
        POST,
        NONE
    }

    private fun importantMessage(text: String): Boolean {
        println("got message: $text")
        return text.contains("TIL", true) && text.contains("littybot", true)
    }

    private fun getAction(text: String): CommandType {
        if(text.contains("unregister", true)) {
            return CommandType.UNREGISTER
        }

        if(text.contains("register", true)) {
            return CommandType.REGISTER
        }

        if(text.contains("save", true)) {
            return CommandType.POST
        }
        return CommandType.NONE
    }

    fun onReactionAdded(authRequest: SlackEventRequest, context: Context) {

    }


}