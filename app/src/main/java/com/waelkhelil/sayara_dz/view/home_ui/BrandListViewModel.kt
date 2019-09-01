package com.waelkhelil.sayara_dz.view.home_ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.waelkhelil.sayara_dz.database.data.BrandsRepository
import com.waelkhelil.sayara_dz.database.model.Brand


/**
 * The ViewModel works with the [brandsRepository] to get the data.
 */

class BrandListViewModel : ViewModel() {

    private var mutableLiveData: MutableLiveData<List<Brand>>? = null
    private var newsRepository: BrandsRepository? = null
    // LiveData of network errors.
    private var networkErrors = MutableLiveData<String>()


    fun init() {
        if (mutableLiveData != null) {
            return
        }
        newsRepository = BrandsRepository.instance
        mutableLiveData = newsRepository!!.getBrands( {error ->
            networkErrors!!.postValue(error)})
        Log.i("neter","${networkErrors.value}")

    }

    fun getNewsRepository(): LiveData<List<Brand>>? {
        return mutableLiveData
    }
    fun getNetworkErrors(): LiveData<String>? {
        return networkErrors
    }

}

