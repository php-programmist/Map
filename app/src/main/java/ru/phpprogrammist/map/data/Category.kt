package ru.phpprogrammist.map.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Category(
    @Json(name = "icon")
    val icon: Icon = Icon(),
    @Json(name = "id")
    val id: String = "",
    @Json(name = "name")
    val name: String = "",
    @Json(name = "pluralName")
    val pluralName: String = "",
    @Json(name = "primary")
    val primary: Boolean = false,
    @Json(name = "shortName")
    val shortName: String = ""
)