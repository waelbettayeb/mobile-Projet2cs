package com.waelkhelil.sayara_dz.database.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.waelkhelil.sayara_dz.database.api.SayaraDzService
import com.waelkhelil.sayara_dz.database.api.loadModels
import com.waelkhelil.sayara_dz.database.db.LocalCache
import com.waelkhelil.sayara_dz.database.model.Model

class ModelBoundaryCallBack (

    private val service: SayaraDzService,
    private val cache: LocalCache,
    private val id_marque: String

) : PagedList.BoundaryCallback<Model>() {

    // LiveData of network errors.
    private val _networkErrors = MutableLiveData<String>()
    val networkErrors : LiveData<String>
        get() = _networkErrors

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    override fun onZeroItemsLoaded() {
        requestAndSaveData(id_marque)
    }

     override  fun onItemAtEndLoaded(itemAtEnd: Model) {
        requestAndSaveData(id_marque)
    }

    private fun requestAndSaveData(id_marque:String) {
        if (isRequestInProgress) return

        isRequestInProgress = true
        loadModels(service, id_marque,  { models ->
            cache.insertModels(models, {

                isRequestInProgress = false
            })

        }, { error ->
            _networkErrors.postValue(error)
            isRequestInProgress = false
        })
    }
}