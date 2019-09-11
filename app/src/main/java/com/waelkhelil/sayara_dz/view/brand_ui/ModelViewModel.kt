package com.waelkhelil.sayara_dz.view.brand_ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.waelkhelil.sayara_dz.database.data.BrandsRepository
import com.waelkhelil.sayara_dz.database.model.Model

class ModelViewModel (private val repository: BrandsRepository,private val id:String) : ViewModel() {

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }
    val models: LiveData<PagedList<Model>> = repository.searchModels(id).data
    val networkErrors: LiveData<String> = repository.searchModels(id).networkErrors
}