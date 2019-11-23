package ru.phpprogrammist.map.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Venue(
    @Json(name = "categories")
    val categories: List<Category> = listOf(),
    @Json(name = "id")
    val id: String = "",
    @Json(name = "location")
    val location: Location = Location(),
    @Json(name = "name")
    val name: String = "",
    @Json(name = "photos")
    val photos: Photos = Photos()
)