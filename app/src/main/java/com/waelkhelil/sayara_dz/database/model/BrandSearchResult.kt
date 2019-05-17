package com.waelkhelil.sayara_dz.database.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

/**
 * brndSearchResult from an api call , which contains LiveData<List<Brand>> holding result  data,
 * and a LiveData<String> of network error state.
 */
data class BrandSearchResult(
    val data: LiveData<PagedList<Brand>>,
    val networkErrors: LiveData<String>
)