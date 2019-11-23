package ru.phpprogrammist.map.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SuggestedFilters(
    @Json(name = "filters")
    val filters: List<Filter> = listOf(),
    @Json(name = "header")
    val header: String = ""
)