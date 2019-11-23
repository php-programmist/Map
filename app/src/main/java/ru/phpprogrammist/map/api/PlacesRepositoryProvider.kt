package ru.phpprogrammist.map.api

object PlacesRepositoryProvider {
    fun providePlacesRepository(): PlacesRepository {
        val apiService = ApiService.create()
        return PlacesRepository(apiService)
    }
}