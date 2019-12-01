package ru.phpprogrammist.map


import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import mumayank.com.airlocationlibrary.AirLocation
import ru.phpprogrammist.map.data.PlacesResponse


class MapFragment : Fragment() {

    private var myLatitude:Double = 55.7537
    private var myLongitude:Double = 37.6198
    private val zoom = 15f
    private var isCoordinatesSets = false
    private lateinit var map: SupportMapFragment
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        map = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        Log.i("geo","View Created")



    }

    override fun onResume() {
        getLocation()
        super.onResume()
    }

    private fun initMap() {
        map.getMapAsync {
            val map = it
            val currentLocation = LatLng(myLatitude, myLongitude)

            map.mapType = GoogleMap.MAP_TYPE_NORMAL


            moveCameraToCurrentPosition(map, currentLocation)

            map.setOnCameraIdleListener {
                Log.i("geo","Camera stops: ${it.cameraPosition}")

                viewModel.searchPlaces(map.cameraPosition.target.latitude,map.cameraPosition.target.longitude).observe(viewLifecycleOwner, Observer { placesResponse ->
                    Log.i("geo","Data changed:")
                    //Log.i("geo",placesResponse.toString())
                    map.clear()
                    map.addMarker(MarkerOptions().position(currentLocation).title("My location").icon(
                            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
                        )
                    )
                    addMarkersFromRequest(placesResponse)
                })
            }
        }
    }

    private fun addMarkersFromRequest(placesResponse: PlacesResponse){
        map.getMapAsync {
            Log.i("geo","Найдено заведений - ${placesResponse.response.groups[0].items.size}")
            for(item in placesResponse.response.groups[0].items){
                addMarkerToMap(it,item.venue.location.lat,item.venue.location.lng,item.venue.name)
            }
        }
    }

    private fun addMarkerToMap(map: GoogleMap,lat:Double, lng:Double, name: String){
        //Log.i("geo","Маркер - $name: $lat,$lng")
        val location = LatLng(lat, lng)
        map.addMarker(MarkerOptions().position(location).title(name))
    }

    private fun moveCameraToCurrentPosition(map: GoogleMap,currentLocation:LatLng ){
        val cameraPosition = CameraPosition.Builder()
            .target(currentLocation)
            .zoom(zoom)
            .build()
        val cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition)
        map.moveCamera(cameraUpdate)
    }



    fun setCoordinates(location:Location){
        myLatitude = location.latitude
        myLongitude = location.longitude
        Log.i("geo","$myLatitude - $myLongitude")
        initMap()
        isCoordinatesSets = true
    }


    private fun getLocation() {
         AirLocation(activity!!,
            shouldWeRequestPermissions = true,
            shouldWeRequestOptimization = true,
            callbacks = object: AirLocation.Callbacks {
                override fun onSuccess(location: Location) {
                    setCoordinates(location)
                }

                override fun onFailed(locationFailedEnum: AirLocation.LocationFailedEnum) {
                    Toast.makeText(activity!!,"$locationFailedEnum", Toast.LENGTH_LONG).show()
                    Log.i("geo","Failed location: $locationFailedEnum")
                    initMap()
                }
            })
    }


}
