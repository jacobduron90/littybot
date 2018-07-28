package com.litty.til.models

import com.google.gson.annotations.SerializedName

data class SlackEvent(@SerializedName("type") val type: SlackEventType,
                      @SerializedName("user") val user: String,
                      @SerializedName("reaction") val reaction: String?,
                      @SerializedName("item") val item: SlackItem?,
                      @SerializedName("channel") val channel: String?,
                      @SerializedName("text") val text: String?,
                      @SerializedName("ts") val ts: String?,
                      @SerializedName("event_ts") val eventTs: String?,
                      @SerializedName("channel_type") val channelType: String?)

data class SlackItem(
        @SerializedName("type") val type: String,
        @SerializedName("channel") val channel: String,
        @SerializedName("ts") val ts: String
)