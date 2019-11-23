package ru.phpprogrammist.map.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "address")
    val address: String = "",
    @Json(name = "cc")
    val cc: String = "",
    @Json(name = "city")
    val city: String = "",
    @Json(name = "country")
    val country: String = "",
    @Json(name = "distance")
    val distance: Int = 0,
    @Json(name = "formattedAddress")
    val formattedAddress: List<String> = listOf(),
    @Json(name = "labeledLatLngs")
    val labeledLatLngs: List<LabeledLatLng> = listOf(),
    @Json(name = "lat")
    val lat: Double = 0.0,
    @Json(name = "lng")
    val lng: Double = 0.0,
    @Json(name = "state")
    val state: String = ""
)