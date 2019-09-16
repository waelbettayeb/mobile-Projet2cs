package com.waelkhelil.sayara_dz.view.home_ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.waelkhelil.sayara_dz.database.api.SayaraDzService
import com.waelkhelil.sayara_dz.database.model.AdResponse
import com.waelkhelil.sayara_dz.database.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    var user: User? = null
    var adsList:List<AdResponse> = emptyList()
    init {
    }


    fun getAlAds(): MutableLiveData<List<AdResponse>> {
        val adsData = MutableLiveData<List<AdResponse>>()

        SayaraDzService.create().getAllAds().enqueue( object : Callback<List<AdResponse>> {
            override fun onResponse(call: Call<List<AdResponse>>, response: Response<List<AdResponse>>) {
                if (response.isSuccessful()) {
                    adsData.setValue(response.body()!!)
                    adsList=response.body()!!
                } else {
                }
            }
            override fun onFailure(call: Call<List<AdResponse>>, t: Throwable) {

            } })
        return adsData
    }
}
