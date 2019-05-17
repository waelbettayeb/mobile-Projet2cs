package com.waelkhelil.sayara_dz.database.api

import android.content.ContentValues.TAG
import android.util.Log
import com.waelkhelil.sayara_dz.database.model.Brand
import com.waelkhelil.sayara_dz.database.model.Model
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * load all the  brands
 * Trigger a request to the Github searchRepo API with the following params:
 * @param query searchRepo keyword
 * @param page request page index
 * @param itemsPerPage number of repositories to be returned by the Github API per page
 *
 * The result of the request is handled by the implementation of the functions passed as params
 * @param onSuccess function that defines how to handle the list of repos received
 * @param onError function that defines how to handle request failure
 */
fun loadAllBrands(service: SayaraDzService,
                  onSuccess: (repos: List<Brand>) -> Unit,
                  onError: (error: String) -> Unit)

{ service.getBrands().enqueue(
        object : Callback<List<Brand>> {
            override fun onFailure(call: Call<List<Brand>>?, t: Throwable) {
                Log.d(TAG, "fail to get data")
                onError(t.message ?: "unknown error")
            }

            override fun onResponse(
                call: Call<List<Brand>>?,
                response: Response<List<Brand>>
            ) {
                Log.d(TAG, "got a response $response")
                if (response.isSuccessful) {
                    val brands = response.body()?: emptyList()
                    onSuccess(brands)
                } else {
                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }
        }
    )
}

fun loadModels(service: SayaraDzService,Id_Marque:String,
               onSuccess: (models: List<Model>) -> Unit,
onError: (error: String) -> Unit)


{ service.getModels(Id_Marque).enqueue(
    object : Callback<List<Model>> {
        override fun onFailure(call: Call<List<Model>>?, t: Throwable) {
            Log.d(TAG, "fail to get data")
            onError(t.message ?: "unknown error")
        }

        override fun onResponse(
            call: Call<List<Model>>?,
            response: Response<List<Model>>
        ) {
            Log.d(TAG, "got a response $response")
            if (response.isSuccessful) {
                val models = response.body()?: emptyList()
                onSuccess(models)
            } else {
                onError(response.errorBody()?.string() ?: "Unknown error")
            }
        }
    }
)
}

/**
 * sayraDz brands  API communication setup via Retrofit.
 *
 */


interface SayaraDzService {

    // Get all the brands
    @GET("marque")
    fun getBrands(): Call<List<Brand>>

    @GET("modele/{Id_Marque}")
    fun getModels(@Path("Id_Marque") id_marque : String
    ): Call<List<Model>>


    companion object {
        private const val BASE_URL = "https://protechsayaradz.herokuapp.com/"

        fun create():SayaraDzService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SayaraDzService::class.java)
        }
    }
}