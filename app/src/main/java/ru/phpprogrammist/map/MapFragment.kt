package ru.phpprogrammist.map


import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import mumayank.com.airlocationlibrary.AirLocation


class MapFragment : Fragment() {

    private var myLatitude:Double = 55.7537
    private var myLongitude:Double = 37.6198
    private val ZOOM = 17f
    private var isCoordinatesSets = false
    private lateinit var map: SupportMapFragment

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
        getLocation()

    }

    override fun onResume() {
        getLocation()
        super.onResume()
    }

    private fun addCurrentLocationToMap(moveCamera:Boolean = false) {
        map.getMapAsync {
            val map = it
            val currentLocation = LatLng(myLatitude, myLongitude)

            map.mapType = GoogleMap.MAP_TYPE_NORMAL
            //map.addMarker(MarkerOptions().position(currentLocation).title("My location"))
            if (moveCamera) {
                moveCameraToCurrentPosition(map, currentLocation)
            }
        }
    }

    private fun moveCameraToCurrentPosition(map: GoogleMap,currentLocation:LatLng ){
        val cameraPosition = CameraPosition.Builder()
            .target(currentLocation)
            .zoom(ZOOM)
            .build()
        val cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition)
        map.moveCamera(cameraUpdate)
    }



    fun setCoordinates(location:Location){
        myLatitude = location.latitude
        myLongitude = location.longitude
        Log.i("geo","$myLatitude - $myLongitude")
        addCurrentLocationToMap(!isCoordinatesSets)
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
                    addCurrentLocationToMap(true)
                }
            })
    }


}
