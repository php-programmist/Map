package ru.phpprogrammist.map.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.phpprogrammist.map.data.PlacesResponse

interface ApiService {
    @GET("explore")
    fun getPlaces(@Query("ll") latLng:  String,
                  @Query("radius") radius: Int,
                  @Query("section") section: String,
                  @Query("client_id") client_id: String,
                  @Query("client_secret") client_secret: String,
                  @Query("v") version: Int): Call<PlacesResponse>

    companion object Factory {
        private const val BASE_URL = "https://api.foursquare.com/v2/venues/"
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}