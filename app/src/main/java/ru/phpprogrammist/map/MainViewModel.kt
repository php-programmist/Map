package ru.phpprogrammist.map

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager
import ru.phpprogrammist.map.api.PlacesRepositoryProvider
import ru.phpprogrammist.map.data.PlacesResponse

class MainViewModel(application: Application) : AndroidViewModel(application){

    private fun getSection(): String {
        val prefs =PreferenceManager.getDefaultSharedPreferences(getApplication())
        val defValue = "coffee"
        return prefs.getString("section",defValue)?:defValue
    }


    fun searchPlaces(lat:Double, lng:Double): MutableLiveData<PlacesResponse> {
        val repository = PlacesRepositoryProvider.providePlacesRepository()
        return repository.getPlaces(lat, lng, getSection())
    }
}