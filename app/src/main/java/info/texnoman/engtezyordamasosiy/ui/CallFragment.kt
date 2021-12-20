package info.texnoman.engtezyordamasosiy.ui
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.telephony.SubscriptionManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.navigation.Navigation
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.model.LatLng
import info.texnoman.engtezyordamasosiy.databinding.FragmentCallBinding
import info.texnoman.engtezyordamasosiy.R
import java.util.jar.Manifest
import android.telephony.TelephonyManager
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService


class CallFragment : Fragment() {

    var _binding: FragmentCallBinding? = null
    val binding get() = _binding!!
    lateinit var fusetLocatonProviderClient: FusedLocationProviderClient
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCallBinding.inflate(inflater, container, false)
       // val mPhoneNumber =(TelephonyManager) requireActivity().getSystemService(Context.TELEPHONY_SERVICE)


              return binding?.root
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btncall.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_callFragment_to_mapFragment)
            //startActivity(Intent(requireContext(), MapsActivity::class.java))
            fusetLocatonProviderClient = FusedLocationProviderClient(requireContext())

            val locationCallback = object : LocationCallback(){
                override fun onLocationResult(result: LocationResult) {
                    super.onLocationResult(result)
                    result.locations.let { locations->
                        for (location in locations){
                            if (location!=null){
                                // saveLocation(LocationBase(location.latitude,location.longitude))
                                val myLocation = LatLng(location.latitude,location.longitude)
                                Log.e("location",myLocation.toString())
                                /*  mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 16F), 500, null)
                                  //mMap.addMarker(MarkerOptions().position(myLocation).title("Marker in Sydney"))
                                  if (icon==null){
                                      icon = mMap.addMarker(
                                          MarkerOptions().position(myLocation)
                                              .icon(bitmapDescriptorFromVector(R.drawable.ic_ambulance))
                                              .anchor(0.5f, 1f))
                                  }else{
                                      icon?.setPosition(myLocation)
                                  }*/


                            }
                        }
                    }
                }
            }

            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                   ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireContext(),
                   ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return@setOnClickListener
            }
            val request = LocationRequest.create().apply {
                interval = 1000
                fastestInterval =1000
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            }
            Looper.myLooper()?.let {
                fusetLocatonProviderClient.requestLocationUpdates(
                    request,
                    locationCallback,
                    it
                )
            }


        }





    }
    /* fun fusedLocation(mMap: GoogleMap) {
         var currentLatLng: LatLng? = null
         var fusedLocationProviderClient = FusedLocationProviderClient(requireContext())
         var locationRequest = LocationRequest.create().apply {
             interval = 60000
             fastestInterval = 60000
             priority = LocationRequest.PRIORITY_HIGH_ACCURACY
         }

         var locationcallback = object : LocationCallback() {
             override fun onLocationResult(locationresult: LocationResult) {
                 super.onLocationResult(locationresult)
                 locationresult ?: return
                 Log.e("currentLocation", currentLatLng.toString())
                 currentLatLng = LatLng(
                     locationresult.locations[locationresult.locations.size - 1].latitude,
                     locationresult.locations[locationresult.locations.size - 1].longitude
                 )
                 for (location in locationresult.locations) {
                     if (currentLatLng == null) {

                     }
                 }
                 /* mMap.animateCamera(
                     CameraUpdateFactory.newLatLng(locationresult.locations), 500, null
                 )*/

             }
         }

         if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
             return
         }
         mMap.isMyLocationEnabled = true
         fusedLocationProviderClient?.requestLocationUpdates(
             locationRequest,
             locationcallback,
             Looper.myLooper()!!
         )
     }*/


}