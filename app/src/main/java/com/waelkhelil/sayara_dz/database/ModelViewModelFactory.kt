package com.waelkhelil.sayara_dz.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.waelkhelil.sayara_dz.view.brand_ui.ModelViewModel
import com.waelkhelil.sayara_dz.model_ui.ModelVersionsViewModel

class ModelViewModelFactory( private val id:String) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ModelViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ModelViewModel(id) as T
        }
        if (modelClass.isAssignableFrom(ModelVersionsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ModelVersionsViewModel(id) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}