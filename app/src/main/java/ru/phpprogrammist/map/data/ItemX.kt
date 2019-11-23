package ru.phpprogrammist.map.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemX(
    @Json(name = "reasonName")
    val reasonName: String = "",
    @Json(name = "summary")
    val summary: String = "",
    @Json(name = "type")
    val type: String = ""
)