package com.litty.til

import com.google.gson.Gson
import com.litty.til.models.RegisterModel


const val FIREBASE_BASE_URL = "https://littybot-45a3c.firebaseio.com/"
const val LISTENER_DOC = "littybot/tags.json"

interface FirebaseClient {

    fun registerListener(room: String, tag: String)

    fun unregisterListener(room: String, tag: String)

    fun getListeners(): List<RegisterModel>
}


class FirebaseClientImpl(val gson: Gson): FirebaseClient {

    val tagMap = mutableMapOf<String, String>()


    override fun registerListener(room: String, tag: String) {
        tagMap[tag] = room
    }

    override fun unregisterListener(room: String, tag: String) {
        tagMap.remove(tag)
    }

    override fun getListeners(): List<RegisterModel> {
        val tagList = mutableListOf<RegisterModel>()
        tagMap.forEach {
            tagList.add(RegisterModel(it.key, it.value))
        }
        return tagList
    }
}