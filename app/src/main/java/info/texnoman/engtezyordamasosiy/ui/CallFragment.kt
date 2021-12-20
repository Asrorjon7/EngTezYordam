package info.texnoman.engtezyordamasosiy.ui
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import info.texnoman.engtezyordamasosiy.databinding.FragmentCallBinding
import info.texnoman.engtezyordamasosiy.MapsActivity

class CallFragment : Fragment() {

    var _binding: FragmentCallBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCallBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btncall.setOnClickListener {
            //    Navigation.findNavController(it).navigate(R.id.action_callFragment_to_mapFragment)
            startActivity(Intent(requireContext(), MapsActivity::class.java))
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