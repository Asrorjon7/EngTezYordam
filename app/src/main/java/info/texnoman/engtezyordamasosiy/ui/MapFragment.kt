package info.texnoman.engtezyordamasosiy.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.iteach.tezyordam.base.Order
import info.texnoman.engtezyordamasosiy.R
import info.texnoman.engtezyordamasosiy.databinding.FragmentMapBinding
import info.texnoman.engtezyordamasosiy.model.ComplitModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MapFragment : Fragment(),
    OnMapReadyCallback {
    var _binding: FragmentMapBinding? = null
    val binding get() = _binding!!
    lateinit var mapFragment: SupportMapFragment
    private lateinit var mMap: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*  val mapFragment = activity?.supportFragmentManager
              ?.findFragmentById(R.id.map) as SupportMapFragment
          mapFragment.getMapAsync(this)*/
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        loadMap()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getLocation()
        yetibkeldi()

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Toast.makeText(requireContext(), googleMap.toString()+"", Toast.LENGTH_SHORT).show()
        /*   val sydney = LatLng(-34.0, 151.0)
           mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
           mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/
    }

    private fun loadMap() {
        mapFragment = (childFragmentManager.findFragmentById(R.id.mapAPI) as SupportMapFragment?)!!
        val fm = childFragmentManager
        val ft = fm.beginTransaction()
        mapFragment = SupportMapFragment.newInstance()
        ft.replace(R.id.mapAPI, mapFragment).commit()
        mapFragment.getMapAsync(this)
    }

    fun getLocation() = CoroutineScope(Dispatchers.IO).launch {
        val personCollectRef = Firebase.firestore.collection("where")
        try {
            personCollectRef.addSnapshotListener { value, error ->
                value.let {
                    if (it != null) {
                        for (document in it.documents) {
                            var latt = document.toObject<LocationBase>()?.latitude
                            var long = document.toObject<LocationBase>()?.logitude
                            mMap.clear()
                            mMap.animateCamera(
                                CameraUpdateFactory.newLatLngZoom(
                                    LatLng(
                                        latt!!,
                                        long!!
                                    ), 18F
                                ), 10, null
                            )
                            mMap.addMarker(
                                MarkerOptions().position(LatLng(latt, long)).title("Shifokor")
                            )
                            Log.e(
                                "locatio21",
                                document.toObject<LocationBase>()?.latitude.toString()
                            )
                        }
                    }
                }
                error.let {
                    if (it != null) {
                        Toast.makeText(requireContext(), it?.message.toString(), Toast.LENGTH_LONG)
                            .show()
                        return@addSnapshotListener
                    }
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    fun yetibkeldi() = CoroutineScope(Dispatchers.IO).launch {
        val personCollectRef = Firebase.firestore.collection("completed")
        try {
            personCollectRef.addSnapshotListener { value, error ->
                value.let {
                    if (it != null) {
                        var orderId: String = ""
                        for (document in it.documents) {
                            if (document.get("phone").toString() == "+998908316339") {
                                //  personCollectRef.document("+998908316339").delete()
                                Log.e("firebasedata", document.toString())
                                orderId = document.toObject<ComplitModel>()?.id!!
                                break
                                Log.e("keldi", "keldida")
                            }
                        }

                        Log.e("orderid", orderId.toString())

                        if (!orderId.equals("")) {
                            Firebase.firestore.collection("completed").document(orderId)
                                .delete().addOnSuccessListener { it ->
                                    Log.e("okey", "Ochdi")
                                }
                                .addOnFailureListener {
                                    Log.e("uchirishdaxatolik", it.toString())
                                }

                        }
                    }
                }
                error.let {
                    if (it != null) {
                        Toast.makeText(requireContext(), it?.message.toString(), Toast.LENGTH_LONG)
                            .show()
                        return@addSnapshotListener
                    }
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

}