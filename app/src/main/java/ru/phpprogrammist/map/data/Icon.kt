package ru.phpprogrammist.map.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Icon(
    @Json(name = "prefix")
    val prefix: String = "",
    @Json(name = "suffix")
    val suffix: String = ""
)