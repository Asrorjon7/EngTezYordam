package info.texnoman.engtezyordamasosiy.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StringSharedViewModel : ViewModel() {
    var string = MutableLiveData<String>()

    fun setString(string: String) {
        this.string.postValue(string)
    }
    var _cartsum =MutableLiveData<String>()
    val cartsum:LiveData<String> get() =_cartsum
    fun setSumString(string:String){
        _cartsum.value =string
    }

}