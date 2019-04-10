package com.waelkhelil.sayara_dz.view.brands_ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.waelkhelil.sayara_dz.model.AppDatabase
import com.waelkhelil.sayara_dz.model.Brand
import com.waelkhelil.sayara_dz.model.BrandRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class BrandsListViewModel(application: Application) : AndroidViewModel(application) {
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: BrandRepository
    val Allbrands: LiveData<List<Brand>>
    init {
        val brandsDao = AppDatabase.getDatabase(application,scope).brandDao()
        repository = BrandRepository(brandsDao)
        Allbrands=repository.brands
    }

    fun insert(brand: Brand) = scope.launch(Dispatchers.IO) {
        repository.insert(brand)
    }
    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

}
