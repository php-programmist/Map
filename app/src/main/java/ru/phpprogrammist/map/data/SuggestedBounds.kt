package ru.phpprogrammist.map.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SuggestedBounds(
    @Json(name = "ne")
    val ne: Ne = Ne(),
    @Json(name = "sw")
    val sw: Sw = Sw()
)