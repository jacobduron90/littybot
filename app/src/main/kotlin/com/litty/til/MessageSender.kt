package com.litty.til

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.litty.til.models.MessageModel

const val SLACK_MESSAGE_URL = "https://slack.com/api/chat.postMessage"
const val AUTH = "Bearer xoxb-404612822295-403372896164-9sD8GVIU3krZE6UPbFRjoNNs"

fun sendMessageToSlack(message: String, room: String) {
    val resp = khttp.post(
        url = SLACK_MESSAGE_URL,
        headers = mapOf(
                Pair("Authorization", AUTH)),
        json = mapOf("token" to AUTH, "text" to message, "channel" to room)
        )
    println("got response from sending message: ${resp.text}")
}