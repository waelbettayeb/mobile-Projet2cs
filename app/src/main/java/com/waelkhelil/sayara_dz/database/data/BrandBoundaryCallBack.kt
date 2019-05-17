package com.waelkhelil.sayara_dz.database.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.waelkhelil.sayara_dz.database.api.SayaraDzService
import com.waelkhelil.sayara_dz.database.api.loadAllBrands
import com.waelkhelil.sayara_dz.database.db.LocalCache
import com.waelkhelil.sayara_dz.database.model.Brand

class BrandBoundaryCallBack (

    private val service: SayaraDzService,
    private val cache: LocalCache
) : PagedList.BoundaryCallback<Brand>() {

    // LiveData of network errors.
    private val _networkErrors = MutableLiveData<String>()
    val networkErrors : LiveData<String>
        get() = _networkErrors

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    override fun onZeroItemsLoaded() {
        requestAndSaveData()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Brand) {
        requestAndSaveData()
    }

    private fun requestAndSaveData() {
        if (isRequestInProgress) return

        isRequestInProgress = true
        loadAllBrands(service,   { brands ->
            cache.insertBrands(brands, {

                isRequestInProgress = false
            })

        }, { error ->
            _networkErrors.postValue(error)
            isRequestInProgress = false
        })
    }
}