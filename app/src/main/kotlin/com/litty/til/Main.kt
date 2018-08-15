package com.litty.til

import com.google.gson.Gson
import com.litty.til.models.SlackEventRequest

import com.litty.til.models.SlackEventType

import io.javalin.Context
import io.javalin.Javalin
import org.slf4j.LoggerFactory


val gson = Gson()
val firebaseClient = FirebaseClientImpl(gson)
val responseResolver = ResponseResolvers(gson, firebaseClient)



fun main(args: Array<String>) {
    val app = Javalin.start(getHerokuAssignedPort())
    app.get("/") { ctx ->
        ctx.result("foo")
    }

    app.post("/slack/events") { ctx ->
        val logger = LoggerFactory.getLogger(SlackEventRequest::class.java)
        logger.info("got request ${ctx.body()}")

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
    ctx.header("Content-type:", "application/json")
    when(baseModel.type) {
        SlackEventType.url_verification -> responseResolver.onUrlVerification(baseModel, ctx)
        SlackEventType.event_callback -> {
            when(baseModel.event.type) {
                SlackEventType.message -> responseResolver.onNewMessage(baseModel, ctx)
                SlackEventType.reaction_added -> responseResolver.onReactionAdded(baseModel, ctx)
                else -> {}
            }
        }
        else -> {}
    }
}