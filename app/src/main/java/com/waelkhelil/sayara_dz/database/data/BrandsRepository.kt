package com.waelkhelil.sayara_dz.database.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.waelkhelil.sayara_dz.database.api.SayaraDzService
import com.waelkhelil.sayara_dz.database.api.SayaraDzService.RetrofitService
import com.waelkhelil.sayara_dz.database.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
* Repository class that works with local and remote data sources.
*/


class BrandsRepository {

    private val SayaraDzApi: SayaraDzService

    init {
        SayaraDzApi = RetrofitService.create()
    }


    fun getBrands(onError: (error: String) -> Unit): MutableLiveData<List<Brand>> {
        val newsData = MutableLiveData<List<Brand>>()
        SayaraDzApi.getBrands().enqueue( object : Callback<List<Brand>> {
            override fun onResponse(call: Call<List<Brand>>, response: Response<List<Brand>>) {
                Log.i("response", "answered")
                if (response.isSuccessful()) {
                    newsData.setValue(response.body()!!)


                    Log.i("success", "success")
                } else {


                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }
            override fun onFailure(call: Call<List<Brand>>, t: Throwable) {


                Log.i("failure", "failed")
                onError(t.message ?: "Unknown error")
            }









        })
        return newsData
    }


    fun getModels(id_marque:String,onError: (error: String) -> Unit): MutableLiveData<List<Model>> {

        val modelData = MutableLiveData<List<Model>>()
        SayaraDzApi.getModels(id_marque).enqueue( object : Callback<List<Model>> {
            override fun onResponse(call: Call<List<Model>>, response: Response<List<Model>>) {
                Log.i("response", "answered")
                if (response.isSuccessful()) {
                    modelData.setValue(response.body()!!)



                    Log.i("success", "success")
                } else {


                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }
            override fun onFailure(call: Call<List<Model>>, t: Throwable) {


                Log.i("failure", "failed")
                onError(t.message ?: "Unknown error")
            }









        })
        return modelData}



    fun getModelColors(id_modele:String,onError: (error: String) -> Unit): MutableLiveData<List<PaintColor>> {

        val colorData = MutableLiveData<List<PaintColor>>()
        SayaraDzApi.getModelColors(id_modele).enqueue( object : Callback<List<PaintColor>> {
            override fun onResponse(call: Call<List<PaintColor>>, response: Response<List<PaintColor>>) {
                Log.i("response color ", "answered")
                if (response.isSuccessful()) {
                    colorData.setValue(response.body()!!)


                    Log.i("success color ", "success")
                } else {


                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }
            override fun onFailure(call: Call<List<PaintColor>>, t: Throwable) {


                Log.i("failure", "failed")
                onError(t.message ?: "Unknown error")
            }









        })
        return colorData}

        fun getVersions(id_modele:String, onError: (error: String) -> Unit): MutableLiveData<List<Version>> {

            val versionData = MutableLiveData<List<Version>>()
            SayaraDzApi.getVersions(id_modele).enqueue( object : Callback<List<Version>> {
                override fun onResponse(call: Call<List<Version>>, response: Response<List<Version>>) {
                    Log.i("response", "answered")
                    if (response.isSuccessful()) {
                        versionData.setValue(response.body()!!)



                        Log.i("success", "success")
                    } else {


                        onError(response.errorBody()?.string() ?: "Unknown error")
                    }
                }
                override fun onFailure(call: Call<List<Version>>, t: Throwable) {


                    Log.i("failure", "failed")
                    onError(t.message ?: "Unknown error")
                }









            })
            return versionData
    }

    fun getVersionPrice(id_version:String, onError: (error: String) -> Unit): MutableLiveData<List<VersionPrice>> {

        val versionData = MutableLiveData<List<VersionPrice>>()
        SayaraDzApi.getVersionPrice(id_version).enqueue( object : Callback<List<VersionPrice>> {
            override fun onResponse(call: Call<List<VersionPrice>>, response: Response<List<VersionPrice>>) {
                Log.i("response", "answered")
                if (response.isSuccessful()) {
                    versionData.setValue(response.body()!!)
                    

                    Log.i("success", "success")
                } else {


                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }
            override fun onFailure(call: Call<List<VersionPrice>>, t: Throwable) {


                Log.i("failure", "failed")
                onError(t.message ?: "Unknown error")
            }









        })
        return versionData
    }

    fun getVersionOptions(id_version:String, onError: (error: String) -> Unit): MutableLiveData<List<Option>> {

        val versionData = MutableLiveData<List<Option>>()
        SayaraDzApi.getVersionOptions(id_version).enqueue( object : Callback<List<Option>> {
            override fun onResponse(call: Call<List<Option>>, response: Response<List<Option>>) {
                Log.i("response", "answered")
                if (response.isSuccessful()) {
                    versionData.setValue(response.body()!!)


                    Log.i("success", "success")
                } else {


                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }
            override fun onFailure(call: Call<List<Option>>, t: Throwable) {


                Log.i("failure", "failed")
                onError(t.message ?: "Unknown error")
            }









        })
        return versionData
    }

    fun getUserOrders(user_email:String, onError: (error: String) -> Unit): MutableLiveData<List<reservation>> {

        val userOrdersList = MutableLiveData<List<reservation>>()
        SayaraDzApi.getUserOrders(user_email).enqueue( object : Callback<List<reservation>> {
            override fun onResponse(call: Call<List<reservation>>, response: Response<List<reservation>>) {
                Log.i("response", "answered")
                if (response.isSuccessful()) {
                    userOrdersList.setValue(response.body()!!)


                    Log.i("success", "success")
                } else {


                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }
            override fun onFailure(call: Call<List<reservation>>, t: Throwable) {


                Log.i("failure", "failed")
                onError(t.message ?: "Unknown error")
            }









        })
        return userOrdersList
    }


    fun getOptionPrice(id_Option:String, onError: (error: String) -> Unit): MutableLiveData<List<OptionPrice>> {

        val OptionPriceData = MutableLiveData<List<OptionPrice>>()
        SayaraDzApi.getOptionPrice(id_Option).enqueue( object : Callback<List<OptionPrice>> {
            override fun onResponse(call: Call<List<OptionPrice>>, response: Response<List<OptionPrice>>) {
                Log.i("response", "answered")
                if (response.isSuccessful()) {
                    OptionPriceData.setValue(response.body()!!)


                    Log.i("success", "success")
                } else {


                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }
            override fun onFailure(call: Call<List<OptionPrice>>, t: Throwable) {


                Log.i("failure", "failed")
                onError(t.message ?: "Unknown error")
            }









        })
        return OptionPriceData
    }



    fun getColorPrice(id_Color:String, onError: (error: String) -> Unit): MutableLiveData<List<ColorPrice>> {

        val colorPriceData = MutableLiveData<List<ColorPrice>>()
        SayaraDzApi.getColorPrice(id_Color).enqueue( object : Callback<List<ColorPrice>> {
            override fun onResponse(call: Call<List<ColorPrice>>, response: Response<List<ColorPrice>>) {
                Log.i("response", "answered")
                if (response.isSuccessful()) {
                    colorPriceData.setValue(response.body()!!)


                    Log.i("success", "success")
                } else {


                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }
            override fun onFailure(call: Call<List<ColorPrice>>, t: Throwable) {


                Log.i("failure", "failed")
                onError(t.message ?: "Unknown error")
            }









        })
        return colorPriceData
    }



    companion object {


    var instance: BrandsRepository? = null
        get() {
            if (field == null) {
                field = BrandsRepository()
            }
            return field
        }}

}

