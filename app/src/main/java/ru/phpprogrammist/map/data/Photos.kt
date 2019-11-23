package ru.phpprogrammist.map.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Photos(
    @Json(name = "count")
    val count: Int = 0,
    @Json(name = "groups")
    val groups: List<Any> = listOf()
)