package ru.phpprogrammist.map.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlacesResponse(
    @Json(name = "meta")
    val meta: Meta = Meta(),
    @Json(name = "response")
    val response: Response = Response()
)