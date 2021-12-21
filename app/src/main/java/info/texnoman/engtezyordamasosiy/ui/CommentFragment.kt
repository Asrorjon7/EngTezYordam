package info.texnoman.engtezyordamasosiy.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.hsalf.smilerating.BaseRating
import info.texnoman.engtezyordamasosiy.ApiViewModel
import info.texnoman.engtezyordamasosiy.R
import info.texnoman.engtezyordamasosiy.databinding.FragmentCommentBinding
import info.texnoman.engtezyordamasosiy.network.RetrofitClient
import info.texnoman.engtezyordamasosiy.utils.OrderIdSave
import info.texnoman.engtezyordamasosiy.utils.Status
import info.texnoman.engtezyordamasosiy.utils.Toolbar
import info.texnoman.texnomart.ViewModelFactory

class CommentFragment : Fragment() {
    var _binding: FragmentCommentBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCommentBinding.inflate(inflater, container, false)
        setUpData()
        return binding.root
    }
    private fun setUpData() {
        binding.apply {
            ratingView.setNameForSmile(BaseRating.TERRIBLE, "Daxshatli")
            ratingView.setNameForSmile(BaseRating.BAD, "Yomon")
            ratingView.setNameForSmile(BaseRating.OKAY, "Qoniqarsiz")
            ratingView.setNameForSmile(BaseRating.GOOD, "Yaxshi")
            ratingView.setNameForSmile(BaseRating.GREAT, "Ajoyib")

        }
        binding.apply {
            btnSetComment.setOnClickListener {
                var rating:Int =ratingView.rating
                var corrupsia:Boolean =swith.isChecked
               var comment:String =etComment.text.toString()

               /* ViewModelProvider(requireActivity(),
                    ViewModelFactory(RetrofitClient.instance)
                )[ApiViewModel::class.java]
                    .setOrder(person.complaint.toString(),person.phone,person.Latitude,person.longitude,person.condition)
                    .observe(viewLifecycleOwner,{status->

                        when(status.status){
                            Status.SUCCESS->status.data.let{
                                OrderIdSave.saveId(it?.data?.id!!)
                                setFirebase(person)
                                Log.e("sdlafhjkgs", OrderIdSave.getId().toString())
                            }
                            Status.ERROR-> status.message.let{
                                Log.e("tostring",it.toString())
                            }
                        }

                    })*/
                }


        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Toolbar(requireActivity(),requireView(),"Izoh qoldiring !",false)

    }

}