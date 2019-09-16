package com.waelkhelil.sayara_dz.database.api

import com.google.gson.JsonObject
import com.waelkhelil.sayara_dz.database.model.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


/**
 * sayraDz brands  API communication sssetup via Retrofit.
 *
 */


interface SayaraDzService {

    // Get all the brands
    @GET("marque")
    fun getBrands(): Call<List<Brand>>

    //  Get models  by brand id
    @GET("modele/{Id_Marque}")
    fun getModels(@Path("Id_Marque") id_marque : String
    ): Call<List<Model>>

    //  Get versions  by modele  id
    @GET("version/{Id_Modele}")
    fun getVersions(@Path("Id_Modele") id_modele : String
    ): Call<List<Version>>


    //get colors available in a model
    @GET("couleur/{Id_Modele}")
    fun getModelColors (@Path("Id_Modele") id_modele : String
    ): Call<List<PaintColor>>

    //get a version  price
    @GET("tarif/version/{Id_Version}")
    fun getVersionPrice(@Path("Id_Version") id_version : String
    ): Call<List<VersionPrice>>

    //get an option   price
    @GET("tarif/option/{Id_Option}")
    fun getOptionPrice(@Path("Id_Option") id_option : String
    ): Call<List<OptionPrice>>

    //get a color price
    @GET("tarif/couleur/{Id_Color}")
    fun getColorPrice(@Path("Id_Color") id_color : String
    ): Call<List<ColorPrice>>


    //get a version  options
    @GET("option/version/{Id_Version}")
    fun getVersionOptions(@Path("Id_Version") id_version : String
    ): Call<List<Option>>

    //post a new order  :
    @POST("reservation/commande/new")
    fun sendReservation(@Body  body: JsonObject):Call<reservation>

    //post a new ad :
    @POST("annonce/CreerAnnonce/")
    fun sendAd(@Body  body: JsonObject):Call<Ad>

    @GET("reservation/vehicule/disponible")
    fun checkAvailable():Call<List<Vehicule>>


    //get a user's accepted oreders :
    @GET("reservation/Automobiliste/{User_Email}")
    fun getUserOrders(@Path("User_Email") user_email : String
    ): Call<List<reservation>>
    //get all system ads :

    @GET("annonce")
    fun getAllAds(): Call<List<AdResponse>>




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