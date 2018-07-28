package com.litty.til.models

import com.google.gson.annotations.SerializedName

data class SlackEventRequest(@SerializedName("token") val token: String,
                             @SerializedName("type") val type: SlackEventType,
                             @SerializedName("challenge") val challenge: String) {

}

data class SlackVerificationResponse(val challenge: String)

enum class SlackEventType() {
    url_verification
}