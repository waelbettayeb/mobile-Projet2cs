package com.waelkhelil.sayara_dz.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.waelkhelil.sayara_dz.database.AppDatabase
import com.waelkhelil.sayara_dz.database.Brand
import com.waelkhelil.sayara_dz.database.BrandDao

class BrandsListViewModel(application: Application) : AndroidViewModel(application) {
    private val mBrandDao:BrandDao = AppDatabase.getDatabase(application).brandDao()

    private val brands: MutableLiveData<List<Brand>> by lazy {
        MutableLiveData<List<Brand>>().also {
            loadBrands()
        }
    }

    fun getBrands(): LiveData<List<Brand>> {
        return brands
    }
    private fun loadBrands(): LiveData<List<Brand>> {
        // TODO Do an asynchronous operation to fetch users.
        return mBrandDao.getAll()
    }
}
