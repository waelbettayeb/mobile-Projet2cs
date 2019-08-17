package com.waelkhelil.sayara_dz.database.api

import com.waelkhelil.sayara_dz.database.model.Brand
import com.waelkhelil.sayara_dz.database.model.Model
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path



/**
 * sayraDz brands  API communication sssetup via Retrofit.
 *
 */


interface SayaraDzService {

    // Get all the brands
    @GET("marque")
    fun getBrands(): Call<List<Brand>>

    @GET("modele/{Id_Marque}")
    fun getModels(@Path("Id_Marque") id_marque : String
    ): Call<List<Model>>


    companion object RetrofitService {
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