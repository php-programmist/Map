package ru.phpprogrammist.map.helpers

import com.google.android.gms.maps.model.LatLng
import kotlin.math.*

object Geo {
    private const val earthRadiusMeters: Int = 6378137

    /**
     * Haversine formula. Giving great-circle distances between two points on a sphere from their longitudes and latitudes.
     * It is a special case of a more general formula in spherical trigonometry, the law of haversines, relating the
     * sides and angles of spherical "triangles".
     *
     * https://rosettacode.org/wiki/Haversine_formula#Java
     *
     * @return Distance in meters
     */
    fun calculateDistance(departure: LatLng, destination: LatLng): Int {
        val dLat = Math.toRadians(destination.latitude - departure.latitude)
        val dLon = Math.toRadians(destination.longitude - departure.longitude)
        val originLat = Math.toRadians(departure.latitude)
        val destinationLat = Math.toRadians(destination.latitude)

        val a = sin(dLat / 2).pow(2.toDouble()) + sin(dLon / 2).pow(2.toDouble()) * cos(originLat) * cos(destinationLat)
        val c = 2 * asin(sqrt(a))
        return (earthRadiusMeters * c).roundToInt()
    }
}