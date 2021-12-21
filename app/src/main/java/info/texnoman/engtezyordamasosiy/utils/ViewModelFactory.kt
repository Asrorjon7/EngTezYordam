package info.texnoman.texnomart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import info.texnoman.engtezyordamasosiy.ApiRepository
import info.texnoman.engtezyordamasosiy.ApiViewModel
import info.texnoman.engtezyordamasosiy.network.Api
import java.lang.IllegalArgumentException

class ViewModelFactory(var service: Api) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApiViewModel::class.java)) {
            return ApiViewModel(ApiRepository(service)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}