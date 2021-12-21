package info.texnoman.engtezyordamasosiy.network

import com.iteach.tezyordam.base.Order
import info.texnoman.engtezyordamasosiy.model.OrderResponse
import retrofit2.http.POST
import retrofit2.http.Query
interface Api {

    @POST("order")
    suspend fun setOrder(
        @Query("complaint") complaint:String,
        @Query("phone") phone:String,
        @Query("latitude") latitude:Double,
        @Query("longitude") longtitude:Double,
        @Query("condition") condition:Int
    ): OrderResponse
  //  complaint=1&phone=+998997256368&date=18:37:59&latitude=40.78965412&longitude=71.89556322&condition=Og'ir
//@POST("comment")

}