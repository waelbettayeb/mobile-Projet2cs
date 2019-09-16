package com.waelkhelil.sayara_dz.view.model_ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



class ModelViewModel : ViewModel() {
    val isFavorite = MutableLiveData<Boolean>(false)

    fun setFavorite(f:Boolean){
        isFavorite.postValue(f)
    }
}