package com.waelkhelil.sayara_dz.database.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * brndSearchResult from an api call , which contains LiveData<List<Brand>> holding result  data,
 * and a LiveData<String> of network error state.
 */
/*data class BrandSearchResult(
    val data: List<Brand>,
    val networkErrors: LiveData<String>
)*/

class BrandSearchResult {
    @SerializedName("brands")
    @Expose
    var brands: List<Brand>? = null

    fun getBrand(): List<Brand> {
        return brands!!
    }
}