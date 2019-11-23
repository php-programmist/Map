package ru.phpprogrammist.map.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Meta(
    @Json(name = "code")
    val code: Int = 0,
    @Json(name = "requestId")
    val requestId: String = ""
)