package com.waelkhelil.sayara_dz.view.brand_ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.waelkhelil.sayara_dz.database.data.BrandsRepository
import com.waelkhelil.sayara_dz.database.model.Model
import com.waelkhelil.sayara_dz.database.model.PaintColor

class ModelViewModel (var id_marque:String) : ViewModel() {

    private var mutableLiveData: MutableLiveData<List<Model>>? = null
    private var colorsList: MutableLiveData<List<PaintColor>>? = null
    private var Repository: BrandsRepository? = null
    // LiveData of network errors.
    private var networkErrors = MutableLiveData<String>()


  /*  companion object {
        private const val VISIBLE_THRESHOLD = 5
    }
    //val models: LiveData<PagedList<Model>> = Repository.searchModels(id_marque).data
    //val networkErrors: LiveData<String> = Repository.searchModels(id).networkErrors*/


    fun init() {
        if (mutableLiveData != null) {
            return
        }
        Repository = BrandsRepository.instance
        mutableLiveData = Repository!!.getModels(id_marque, {error ->
            networkErrors!!.postValue(error)})





    }

        fun getRepository(): LiveData<List<Model>>? {
        return mutableLiveData
    }
    fun initColor (id_modele: String)
    {
        colorsList = Repository!!.getModelColors(id_modele, {error ->
            networkErrors!!.postValue(error)})
    }

    fun getModelColors(): LiveData<List<PaintColor>>? {

        return colorsList
    }
    fun getNetworkErrors(): LiveData<String>? {
        return networkErrors



    }}
