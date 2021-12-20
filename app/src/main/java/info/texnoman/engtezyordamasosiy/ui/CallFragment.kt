package info.texnoman.engtezyordamasosiy.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import info.texnoman.engtezyordamasosiy.R
import info.texnoman.engtezyordamasosiy.databinding.FragmentCallBinding


class CallFragment : Fragment() {

    var _binding: FragmentCallBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCallBinding.inflate(inflater, container, false)
        return binding?.root
        //  return inflater.inflate(R.layout.fragment_call, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
     binding.btncall.setOnClickListener {
         Navigation.findNavController(it).navigate(R.id.action_callFragment_to_mapFragment)

     }
    }


}