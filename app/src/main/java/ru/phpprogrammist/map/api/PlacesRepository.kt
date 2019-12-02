package ru.phpprogrammist.map.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.phpprogrammist.map.data.PlacesResponse

class PlacesRepository(private val apiService: ApiService) {
    private val clientId = "JLMXOP3VWT5E3WKUWG1MRD1OLTX0VNX1J5SLV205U3GAFVSS"
    private val clientSecret = "RDVGOPEJDXGMPZUESRFU3JT0IIY5NGX4CBFT0X1W3J4TNNCW"
    private val version = 20190630
    val placesData: MutableLiveData<PlacesResponse> = MutableLiveData()
    fun getPlaces(
        lat: Double,
        lng: Double,
        radius: Int,
        section: String
    ): MutableLiveData<PlacesResponse> {
        Log.i("geo", "Request places send")
        apiService.getPlaces("$lat,$lng", radius, section, clientId, clientSecret, version)
            .enqueue(object :
                Callback<PlacesResponse> {
                override fun onResponse(
                    call: Call<PlacesResponse>,
                    response: Response<PlacesResponse>
                ) {
                    Log.i("geo", "Got response")
                    if (response.isSuccessful) {
                        Log.i("geo", "Got successful response")
                        placesData.value = response.body()
                    } else {
                        Log.e("geo", "Got not successful response: $response")
                    }
                }

                override fun onFailure(
                    call: Call<PlacesResponse>,
                    t: Throwable
                ) {
                    Log.e("geo", "Error during PlacesRepository:getPlaces: {${t.message}}")
                    //PlacesResponse.setValue(null)
                }
            })
        return placesData
    }
}