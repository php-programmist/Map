package ru.phpprogrammist.map.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Response(
    @Json(name = "groups")
    val groups: List<Group> = listOf(),
    @Json(name = "headerFullLocation")
    val headerFullLocation: String = "",
    @Json(name = "headerLocation")
    val headerLocation: String = "",
    @Json(name = "headerLocationGranularity")
    val headerLocationGranularity: String = "",
    @Json(name = "query")
    val query: String = "",
    @Json(name = "suggestedBounds")
    val suggestedBounds: SuggestedBounds = SuggestedBounds(),
    @Json(name = "suggestedFilters")
    val suggestedFilters: SuggestedFilters = SuggestedFilters(),
    @Json(name = "suggestedRadius")
    val suggestedRadius: Int = 0,
    @Json(name = "totalResults")
    val totalResults: Int = 0
)