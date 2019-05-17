package com.waelkhelil.sayara_dz.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.waelkhelil.sayara_dz.database.data.BrandsRepository
import com.waelkhelil.sayara_dz.view.brand_ui.ModelViewModel

class ModelViewModelFactory(private val repository: BrandsRepository,private val id_marque:String) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ModelViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ModelViewModel(repository,id_marque) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}