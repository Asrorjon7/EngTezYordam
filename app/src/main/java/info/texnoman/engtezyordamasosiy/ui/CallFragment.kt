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
import com.google.android.gms.tasks.OnSuccessListener

import android.R.string.no
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.iteach.tezyordam.base.Order
import info.texnoman.engtezyordamasosiy.ApiViewModel
import info.texnoman.engtezyordamasosiy.network.RetrofitClient
import info.texnoman.engtezyordamasosiy.utils.OrderIdSave
import info.texnoman.engtezyordamasosiy.utils.Status
import info.texnoman.engtezyordamasosiy.utils.Toolbar
import info.texnoman.texnomart.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_call.*
import kotlinx.coroutines.*


class CallFragment : Fragment() {
    var _binding: FragmentCallBinding? = null
    val binding get() = _binding!!
    lateinit var fusetLocatonProviderClient: FusedLocationProviderClient

    var currentLatLng: LatLng? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCallBinding.inflate(inflater, container, false)
        getLocation()
        return binding?.root
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Toolbar(requireActivity(),requireView(),"Tez yordam",true)
        binding.btncall.setOnClickListener {
          var condition:Int= 2
            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    R.id.radioTypeMale -> {
                     condition =2
                    }
                    R.id.radioTypeFemale -> {
                     condition =1
                    }
                }
            }
 try {
     var person =Order(etIllnes.text.toString(),"+998908316339",System.currentTimeMillis(),
         currentLatLng!!.latitude, currentLatLng!!.longitude,condition.toInt())
     saveOrder(person)
 }catch (e:Exception){
     Log.e("fkegoubuioegerg",e.toString())
 }


         }
    }
    private fun saveOrder(person: Order) = CoroutineScope(Dispatchers.IO).launch {
        withContext(Dispatchers.Main){
        ViewModelProvider(requireActivity(),ViewModelFactory(RetrofitClient.instance))[ApiViewModel::class.java]
            .setOrder(person.complaint.toString(),person.phone,person.Latitude,person.longitude,person.condition)
            .observe(viewLifecycleOwner,{status->

                when(status.status){
                    Status.SUCCESS->status.data.let{
                        OrderIdSave.saveId(it?.data?.id!!)
                        setFirebase(person)
                    Log.e("sdlafhjkgs",OrderIdSave.getId().toString())
                    }
                    Status.ERROR-> status.message.let{
                        Log.e("tostring",it.toString())
                    }
                }

            })
        }
    }

    fun  setFirebase(person: Order)= CoroutineScope(Dispatchers.IO).launch{
        val personCollectRef = Firebase.firestore.collection("persons")
        try {
            personCollectRef
                .add(person)
                .addOnSuccessListener {
                    Navigation.findNavController(requireView()).navigate(R.id.action_callFragment_to_mapFragment)

                }
                .addOnFailureListener {
                    Toast.makeText(requireActivity(),it.message,Toast.LENGTH_LONG).show()
                }


        }catch (e:Exception){
            withContext(Dispatchers.Main){
                Toast.makeText(requireContext(),e.message,Toast.LENGTH_LONG).show()
            }
        }
    }
    fun getLocation() {
        fusetLocatonProviderClient = FusedLocationProviderClient(requireContext())
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationresult: LocationResult) {
                super.onLocationResult(locationresult)
                locationresult ?: return
                currentLatLng = LatLng(
                    locationresult.locations[locationresult.locations.size - 1].latitude,
                    locationresult.locations[locationresult.locations.size - 1].longitude)
                Log.e("location",currentLatLng.toString())

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
            return
        }
        val request = LocationRequest.create().apply {
            interval = 2000
            fastestInterval = 2000
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