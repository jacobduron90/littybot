package com.litty.til.models

import com.google.gson.annotations.SerializedName

data class SlackEventRequest(@SerializedName("token") val token: String,
                             @SerializedName("type") val type: SlackEventType,
                             @SerializedName("team_id") val teamId: String,
                             @SerializedName("api_app_id") val apiAppId: String,
                             @SerializedName("event_id") val eventId: String,
                             @SerializedName("challenge") val challenge: String,
                             @SerializedName("event") val event: SlackEvent) {

}

data class SlackVerificationResponse(val challenge: String)

