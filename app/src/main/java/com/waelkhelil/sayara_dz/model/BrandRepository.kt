package com.waelkhelil.sayara_dz.model

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class BrandRepository (private val brandDao: BrandDao)
{
    val brands: LiveData<List<Brand>> = brandDao.getAll()
    @WorkerThread
    suspend fun insert(brand: Brand) {
        brandDao.insertAll(brand)
    }
}