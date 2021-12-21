package info.texnoman.engtezyordamasosiy.utils

import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.toolbar.view.*

fun Toolbar(context: FragmentActivity, view: View,title:String,boolean: Boolean) {
    var sharemodel = ViewModelProvider(context).get(StringSharedViewModel::class.java)
    sharemodel.setString(title)
    if (boolean){
        view.ivBack.visibility =View.GONE
    }else{
        view.ivBack.visibility =View.VISIBLE
    }
    sharemodel.string.observe(context, Observer {
        view.toolbar_title.text = it.toString() })
    view.ivBack.setOnClickListener {
        Navigation.findNavController(view).popBackStack()
    }
}