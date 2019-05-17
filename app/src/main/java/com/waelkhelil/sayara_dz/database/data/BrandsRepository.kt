package com.waelkhelil.sayara_dz.database.data

import androidx.paging.LivePagedListBuilder
import com.waelkhelil.sayara_dz.database.api.SayaraDzService
import com.waelkhelil.sayara_dz.database.db.LocalCache
import com.waelkhelil.sayara_dz.database.model.BrandSearchResult
import com.waelkhelil.sayara_dz.database.model.ModelSearchResult

/**
* Repository class that works with local and remote data sources.
*/


class BrandsRepository(private val service: SayaraDzService, private val cache: LocalCache) {

    companion object {
        private const val DATABASE_PAGE_SIZE =25
    }

    fun searchBrands(): BrandSearchResult {


        // Get data source factory from the local cache
        val dataSourceFactory = cache.allBrands()

        // Construct the boundary callback
        val boundaryCallback = BrandBoundaryCallBack( service, cache)
        val networkErrors = boundaryCallback.networkErrors

        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()

        // Get the network errors exposed by the boundary callback
        return BrandSearchResult(data, networkErrors)
    }



    fun searchModels(id_marque :String ): ModelSearchResult {


        // Get data source factory from the local cache
        val dataSourceFactory = cache.allModels(id_marque)

        // Construct the boundary callback
        val boundaryCallback = ModelBoundaryCallBack( service, cache,id_marque )
        val networkErrors = boundaryCallback.networkErrors

        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()

        // Get the network errors exposed by the boundary callback
        return ModelSearchResult(data, networkErrors)
    }
    }




