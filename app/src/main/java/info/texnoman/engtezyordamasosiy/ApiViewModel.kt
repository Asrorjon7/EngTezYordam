package info.texnoman.engtezyordamasosiy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import info.texnoman.engtezyordamasosiy.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

data class ApiViewModel(private val repo: ApiRepository) : ViewModel() {
    fun setOrder(complaint: String,phone:String, lat: Double, long: Double, condition: Int) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {

                emit(Resource.success(data = repo.setOrder(complaint,phone, lat, long, condition)))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Xatoliklar"))
            }
        }
}