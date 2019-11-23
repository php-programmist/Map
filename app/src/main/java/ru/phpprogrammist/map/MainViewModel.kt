package ru.phpprogrammist.map

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.phpprogrammist.map.api.PlacesRepositoryProvider
import ru.phpprogrammist.map.data.PlacesResponse

class MainViewModel(application: Application) : AndroidViewModel(application){
    var places = MutableLiveData<PlacesResponse>()

    fun searchPlaces(lat:Double, lng:Double, section: String){
        val repository = PlacesRepositoryProvider.providePlacesRepository()
        places = repository.getPlaces(lat, lng, section)
    }
}