package com.waelkhelil.sayara_dz.view.add_listing

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.waelkhelil.sayara_dz.database.api.SayaraDzService
import com.waelkhelil.sayara_dz.database.data.BrandsRepository
import com.waelkhelil.sayara_dz.database.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class AddListingViewModel  : ViewModel() {

    val mBitmaps: MutableLiveData<List<Bitmap>> = MutableLiveData(listOf())
    private val mNewBitmaps = mutableListOf<Bitmap>()
    private var Repository: BrandsRepository? = null
    private var networkErrors = MutableLiveData<String>()
    val mListing: MutableLiveData<List<AdResponse>> = MutableLiveData(listOf())
    private val newListing = mutableListOf<AdResponse>()

    init {
        Repository = BrandsRepository.instance
    }

    fun addBitmaps(vararg bitmap: Bitmap){
        mNewBitmaps.addAll(bitmap)
        mBitmaps.postValue(mNewBitmaps)
    }
    fun freeBitmaps(){
        mNewBitmaps.clear()
        mBitmaps.postValue(mNewBitmaps)
    }
    fun addListing(vararg listing: AdResponse){
        newListing.addAll(listing)
        mListing.postValue(newListing)
        freeBitmaps()
    }
    fun getSystemBrands():MutableLiveData<List<Brand>>
    {
        return Repository!!.getBrands{error ->
            networkErrors!!.postValue(error)}

    }
    fun getBrandModels(id_brand:String):MutableLiveData<List<Model>>
    {
        return Repository!!.getModels(id_brand,{error ->
            networkErrors!!.postValue(error)})

    }

    fun getModelVersions(id_modele:String):MutableLiveData<List<Version>>
    {
        return Repository!!.getVersions(id_modele,{error ->
            networkErrors!!.postValue(error)})

    }
    fun getModelColors(id_modele:String):MutableLiveData<List<PaintColor>>
    {
        return Repository!!.getModelColors(id_modele,{error ->
            networkErrors!!.postValue(error)})

    }
    fun getVersionOptions(id_version:String):MutableLiveData<List<Option>>
    {
        return Repository!!.getVersionOptions(id_version,{error ->
            networkErrors!!.postValue(error)})

    }




    fun sendAd(user_email:String,description:String,version_id:String,color_id:String,min_price:Float,options:List<String>):MutableLiveData<String>
    {
        var result :MutableLiveData<String> = MutableLiveData("")

        val paramObject = JsonObject()
        val optionsList = JsonArray()
        for ( item:String in options){
        optionsList.add(item)}
        // pick current system date
        var dt: Date = Date();

        // set format for date
        var dateFormat : SimpleDateFormat =  SimpleDateFormat("yyyy-MM-dd");

        // parse it like
        var current_date:String = dateFormat.format(dt);
        paramObject.addProperty( "Id_Automobiliste", user_email)
        paramObject.addProperty( "Description", description)
        paramObject.addProperty( "Couleur",color_id)
        paramObject.addProperty( "Version",version_id)
        paramObject.addProperty( "Prix_Minimal",min_price)
        paramObject.add("Options",optionsList)
        paramObject.addProperty("date",current_date)





        SayaraDzService.create().sendAd(paramObject).
            enqueue(
                object : Callback<Ad> {
                    override fun onResponse(call: Call<Ad>, response: Response<Ad>) {

                        if (response.isSuccessful()) {
                            result.postValue("votre annonce a bien été envoyée")
                        } else {


                            if (response.code() == 400) {

                            }

                        }
                    }
                    override fun onFailure(call: Call<Ad>, t: Throwable) {

                    }


                })
        return result
    }
}