package info.texnoman.engtezyordamasosiy
import info.texnoman.engtezyordamasosiy.network.Api

class ApiRepository(private val api:Api) {
    suspend fun setOrder(complaint:String,phone:String,lat:Double,long:Double,condition:Int) =
        api.setOrder(complaint,phone,lat,long,condition)
}