package com.litty.til

import com.google.gson.Gson
import com.litty.til.models.SlackEventRequest

import com.litty.til.models.SlackEventType

import io.javalin.Context
import io.javalin.Javalin


val gson = Gson()
fun main(args: Array<String>) {
    val app = Javalin.start(getHerokuAssignedPort())
    app.get("/") { ctx ->
        ctx.result("foo")
    }

    app.post("/slack/events") { ctx ->
        val baseModel = gson.fromJson<SlackEventRequest>(ctx.body(), SlackEventRequest::class.java)
        println(baseModel)
        requestRouter(baseModel, ctx)
    }
}


private fun getHerokuAssignedPort(): Int {
    val processBuilder = ProcessBuilder()
    return if (processBuilder.environment()["PORT"] != null) {
        Integer.parseInt(processBuilder.environment()["PORT"])
    } else 7000
}

fun requestRouter(baseModel: SlackEventRequest, ctx: Context) {
    ctx.header("Content type:", "application/json")
    when(baseModel.type) {
        SlackEventType.url_verification -> ResponseResolvers(baseModel, ctx, gson).apply {
            onRequestReceived()
        }
    }
}