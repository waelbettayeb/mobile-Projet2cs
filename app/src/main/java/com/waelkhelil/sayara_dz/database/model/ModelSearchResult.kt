package com.waelkhelil.sayara_dz.database.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class ModelSearchResult(
    val data: LiveData<PagedList<Model>>,
    val networkErrors: LiveData<String>
)