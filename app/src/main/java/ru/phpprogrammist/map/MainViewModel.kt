package ru.phpprogrammist.map

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.phpprogrammist.map.api.PlacesRepositoryProvider
import ru.phpprogrammist.map.data.PlacesResponse

class MainViewModel(application: Application) : AndroidViewModel(application){
    fun searchPlaces(lat:Double, lng:Double, section: String): MutableLiveData<PlacesResponse> {
        val repository = PlacesRepositoryProvider.providePlacesRepository()
        return repository.getPlaces(lat, lng, section)
    }
}