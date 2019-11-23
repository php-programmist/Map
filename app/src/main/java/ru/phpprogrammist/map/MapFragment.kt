package ru.phpprogrammist.map


import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapFragment : Fragment() {

    private var myLatitude = 55.7537
    private var myLongitude= 37.6198
    private val ZOOM = 17f
    private var isCoordinatesSets = false
    lateinit var locationRequest: LocationRequest
    lateinit var locationCallback: LocationCallback
    lateinit var mFusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity!!)
        locationRequest = LocationRequest.create()
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        locationRequest.setInterval(10 * 1000.toLong()) // 10 seconds

        locationRequest.setFastestInterval(5 * 1000.toLong()) // 5 seconds

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                    location?.let { setCoordinates(it) }
                }
            }
        }
        getLocation()

    }

    private fun addCurrentLocationToMap(moveCamera:Boolean = false) {
        (childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment).getMapAsync {
            val map = it
            val currentLocation = LatLng(myLatitude, myLongitude)

            map.mapType = GoogleMap.MAP_TYPE_NORMAL
            map.addMarker(MarkerOptions().position(currentLocation).title("My location"))
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1000 -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty()
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    getLocation()
                } else {
                    addCurrentLocationToMap(true)
                    Toast.makeText(activity, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
            else->Log.i("geo","Permission code:$requestCode")
        }
    }

    fun setCoordinates(location:Location){
        myLatitude = location.latitude
        myLongitude = location.longitude
        Log.i("geo","$myLatitude - $myLongitude")
        addCurrentLocationToMap(!isCoordinatesSets)
        isCoordinatesSets = true
        mFusedLocationClient.removeLocationUpdates(locationCallback)
        Log.i("geo","Stop polling")
    }


    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(
                activity!!.applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                activity!!.applicationContext,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity!!,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                1000
            )
        } else {
            /*mFusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                null
            )*/
            mFusedLocationClient.getLastLocation().addOnSuccessListener(activity!!
            ) { location ->
                location?.let { setCoordinates(it) }
                    ?: mFusedLocationClient.requestLocationUpdates(
                        locationRequest,
                        locationCallback,
                        null
                    )
            }
        }
    }


}
