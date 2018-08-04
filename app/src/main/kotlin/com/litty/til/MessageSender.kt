package com.litty.til

import com.google.gson.Gson
import com.litty.til.models.MessageModel

const val SLACK_MESSAGE_URL = "https://slack.com/api/chat.postMessage"
const val AUTH = "xoxb-404612822295-403372896164-9sD8GVIU3krZE6UPbFRjoNNs"

fun sendMessageToSlack(message: String, room: String, gson: Gson, token: String) {
    val resp = khttp.post(
        url = SLACK_MESSAGE_URL,
        headers = mapOf(
                Pair("Content-Type", "application/json"),
                Pair("Authorization", AUTH)),
        data = gson.toJson(MessageModel(
                token = token,
                channel = room,
                message = message
        ))
    )
    println("got response from sending message: ${resp.text}")
}