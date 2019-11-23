package ru.phpprogrammist.map.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Item(
    @Json(name = "flags")
    val flags: Flags = Flags(),
    @Json(name = "reasons")
    val reasons: Reasons = Reasons(),
    @Json(name = "referralId")
    val referralId: String = "",
    @Json(name = "venue")
    val venue: Venue = Venue()
)