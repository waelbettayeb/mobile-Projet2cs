package com.waelkhelil.sayara_dz.view.model_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData



class ModelViewModel : ViewModel() {
    val isFavorite = MutableLiveData<Boolean>(false)

    fun setFavorite(f:Boolean){
        isFavorite.postValue(f)
    }
}