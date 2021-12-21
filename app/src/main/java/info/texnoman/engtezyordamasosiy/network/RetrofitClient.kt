package info.texnoman.engtezyordamasosiy.network

import com.google.gson.GsonBuilder
import info.texnoman.engtezyordamasosiy.BuildConfig


import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object RetrofitClient {
    private var retrofit1: Retrofit? = null
    private var api: Api? = null
    private val client =
        OkHttpClient.Builder().also { client ->
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                client.addInterceptor(logging)
            }

        }.build()

    val instance: Api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            //.client(generateClient())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(Api::class.java)
    }
   /* fun getApiClient(): Api {
        retrofit1 =null
        if (retrofit1 == null) {
            retrofit1 = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                //.client(okHttpClient3)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
            api = retrofit1!!.create(Api::class.java)
        }
        return api!!
    }*/




    fun generateClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addInterceptor {
            val request = it.request()
            val url = request.url.newBuilder()
                .build()
            val newRequest = it.request().newBuilder().url(url).build()
            it.proceed(newRequest)
        }
        client.connectTimeout(10, TimeUnit.SECONDS)
        client.readTimeout(30, TimeUnit.SECONDS)
        return client.build()
    }





   /* private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            //  val savedToken = Paper.book().read<String>("token")
            var token = ""
            /*  if (savedToken != null) {
                  token = savedToken
              }*/
            val requestBuilder = original.newBuilder()
                .addHeader("token", token)
                // .addHeader("Accept-Language", Language.getLanguage())
                .method(original.method, original.body)
            val requset = requestBuilder.build()
            chain.proceed(requset)
        }.build()*/

      /*val instance: Api by lazy {
          val retrofit = Retrofit.Builder()
              .baseUrl()
              .client(client)
              //.client(generateClient())
              .addConverterFactory(ScalarsConverterFactory.create())
              .addConverterFactory(GsonConverterFactory.create())
              .build()
          retrofit.create(Api::class.java)
      }*/
    /*  fun getApiClient(baseUrl:String): Api {
          if (retrofit1== null) {
              retrofit1 = Retrofit.Builder()
                  .baseUrl(baseUrl)
                  .client(client)
                  .addConverterFactory(GsonConverterFactory.create())
                  .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                  .build()
             api =retrofit1!!.create(Api::class.java)
          }
          return api!!
      }*/
    val gson = GsonBuilder()
        .setLenient()
        .create();
    /* val okHttpClient3 = OkHttpClient.Builder()
         .addInterceptor(BasicAuthInterceptor("newsite", "newsite"))
         .readTimeout(100, TimeUnit.SECONDS)
         .connectTimeout(100, TimeUnit.SECONDS)
         .build()*/
    /* val testVoyinstance: Api by lazy {
         val retrofit = Retrofit.Builder()
             .baseUrl(Constants.FirstCTestovoyUrl)
             .client(okHttpClient3)
             .addConverterFactory(ScalarsConverterFactory.create())
             .addConverterFactory(GsonConverterFactory.create(gson))
             .build()
         retrofit.create(Api::class.java)
     }*/

    /* private val okHttpClient1 = OkHttpClient.Builder()
         .addInterceptor { chain ->
             val original = chain.request()
             val base = "newsite:newsite"
             var basic = "Basic "+Base64.encodeToString(base.toByteArray(), Base64.NO_WRAP)
             val requestBuilder = original.newBuilder()
                 .header("Authorization",basic)
                 .method(original.method, original.body)
             val requset = requestBuilder.build()
             chain.proceed(requset)
         }.build()
     */
}