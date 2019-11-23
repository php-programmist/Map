package ru.phpprogrammist.map.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Reasons(
    @Json(name = "count")
    val count: Int = 0,
    @Json(name = "items")
    val items: List<ItemX> = listOf()
)