package com.litty.til

import com.google.gson.Gson
import com.litty.til.models.SlackEventRequest
import com.litty.til.models.SlackVerificationResponse
import io.javalin.Context

class ResponseResolvers(val authRequest: SlackEventRequest,
                        val context: Context,
                        val gson: Gson) {


    fun onRequestReceived() {
        val verificationResponse = SlackVerificationResponse(challenge = authRequest.challenge)
        context.status(200)
        context.result(gson.toJson(verificationResponse))
    }


}