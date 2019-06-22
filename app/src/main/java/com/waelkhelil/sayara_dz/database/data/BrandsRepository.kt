package com.waelkhelil.sayara_dz.database.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.waelkhelil.sayara_dz.database.api.SayaraDzService
import com.waelkhelil.sayara_dz.database.api.SayaraDzService.RetrofitService
import com.waelkhelil.sayara_dz.database.model.Brand
import com.waelkhelil.sayara_dz.database.model.Model
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
* Repository class that works with local and remote data sources.
*/


/*class BrandsRepository(private val service: SayaraDzService) {

    companion object {
        private const val DATABASE_PAGE_SIZE =25
    }
    private val _networkErrors = MutableLiveData<String>()
    val networkErrors : LiveData<String>
        get() = _networkErrors

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    fun searchBrands(): BrandSearchResult {


        /* Get data source factory from the local cache
        val dataSourceFactory = cache.allBrands()

        // Construct the boundary callback
        val boundaryCallback = BrandBoundaryCallBack( service, cache)
        val networkErrors = boundaryCallback.networkErrors

        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()*/

        // Get the network errors exposed by the boundary callback
        return BrandSearchResult(requestAndSaveData(), networkErrors)
    }
     fun requestAndSaveData():List<Brand> {
         val  list:ArrayList<Brand> = ArrayList<Brand>()
         loadAllBrands(service,   { brands ->


                list.addAll(brands)


        }, { error ->
            _networkErrors.postValue(error)

        })
         Log.i("infooo","${list.size}")
        return list
    }


    /*fun searchModels(id_marque :String ): ModelSearchResult {


        /*Get data source factory from the local cache
        val dataSourceFactory = cache.allModels(id_marque)

        // Construct the boundary callback
        val boundaryCallback = ModelBoundaryCallBack( service, cache,id_marque )
        val networkErrors = boundaryCallback.networkErrors

        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()*/
        var data :LiveData<Model> = null

        // Get the network errors exposed by the boundary callback
        return ModelSearchResult(data, networkErrors)
    }*/
    }




*/





class BrandsRepository {

    private val newsApi: SayaraDzService

    init {
        newsApi = RetrofitService.create()
    }


    fun getBrands(onError: (error: String) -> Unit): MutableLiveData<List<Brand>> {
        val newsData = MutableLiveData<List<Brand>>()
        newsApi.getBrands().enqueue( object : Callback<List<Brand>> {
            override fun onResponse(call: Call<List<Brand>>, response: Response<List<Brand>>) {
                Log.i("response", "answered")
                if (response.isSuccessful()) {
                    newsData.setValue(response.body()!!)


                    Log.i("success", "success")
                } else {


                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }
            override fun onFailure(call: Call<List<Brand>>, t: Throwable) {


                Log.i("failure", "failed")
                onError(t.message ?: "Unknown error")
            }









        })
        return newsData
    }


    fun getModels(id_marque:String,onError: (error: String) -> Unit): MutableLiveData<List<Model>> {

        val modelData = MutableLiveData<List<Model>>()
        newsApi.getModels(id_marque).enqueue( object : Callback<List<Model>> {
            override fun onResponse(call: Call<List<Model>>, response: Response<List<Model>>) {
                Log.i("response", "answered")
                if (response.isSuccessful()) {
                    modelData.setValue(response.body()!!)


                    Log.i("success", "success")
                } else {


                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }
            override fun onFailure(call: Call<List<Model>>, t: Throwable) {


                Log.i("failure", "failed")
                onError(t.message ?: "Unknown error")
            }









        })
        return modelData
    }

companion object {


    var instance: BrandsRepository? = null
        get() {
            if (field == null) {
                field = BrandsRepository()
            }
            return field
        }}

}

