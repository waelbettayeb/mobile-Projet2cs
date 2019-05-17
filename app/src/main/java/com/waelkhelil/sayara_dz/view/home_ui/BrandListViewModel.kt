package com.waelkhelil.sayara_dz.view.home_ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.waelkhelil.sayara_dz.database.data.BrandsRepository
import com.waelkhelil.sayara_dz.database.model.Brand

/**
 * The ViewModel works with the [brandsRepository] to get the data.
 */
class BrandListViewModel(private val repository: BrandsRepository) : ViewModel() {

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }



    val brands: LiveData<PagedList<Brand>> = repository.searchBrands().data
    val networkErrors: LiveData<String> = repository.searchBrands().networkErrors







}