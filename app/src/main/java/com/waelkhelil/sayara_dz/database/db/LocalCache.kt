package com.waelkhelil.sayara_dz.database.db

import android.util.Log
import androidx.paging.DataSource
import com.waelkhelil.sayara_dz.database.model.Brand
import com.waelkhelil.sayara_dz.database.model.Model
import java.util.concurrent.Executor


/**
 * Class that handles the DAO local data source. This ensures that methods are triggered on the
 * correct executor.
 */
class LocalCache(
    private val brandDao: BrandDao,
    private val modelDao: ModelDao,
    private val ioExecutor: Executor) {

    /**
     * Insert a list of brands  in the database, on a background thread.
     */
    fun insertBrands(brands: List<Brand>, insertFinished: ()-> Unit) {
        ioExecutor.execute {
            Log.d("LocalCache", "inserting ${brands.size} brands")
            brandDao.insert(brands)
            insertFinished()
        }
    }
    fun insertModels(models: List<Model>, insertFinished: ()-> Unit) {
        ioExecutor.execute {
            Log.d("LocalCache", "inserting ${models.size} models")
            modelDao.insert(models)
            insertFinished()
        }
    }

    /**
     * Request a LiveData<List<Brand>> from the Dao
     */
    fun allBrands(): DataSource.Factory<Int, Brand> {
        return brandDao.allBrands()
    }


    fun allModels(id_marque:String): DataSource.Factory<Int, Model> {
        return modelDao.allModels(id_marque)
    }
}