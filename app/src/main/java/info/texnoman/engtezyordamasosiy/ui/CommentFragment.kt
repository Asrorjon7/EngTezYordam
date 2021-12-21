package info.texnoman.engtezyordamasosiy.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hsalf.smilerating.BaseRating
import info.texnoman.engtezyordamasosiy.R
import info.texnoman.engtezyordamasosiy.databinding.FragmentCommentBinding
import info.texnoman.engtezyordamasosiy.utils.Toolbar

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
                }


        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Toolbar(requireActivity(),requireView(),"Izoh qoldiring !",false)

    }

}