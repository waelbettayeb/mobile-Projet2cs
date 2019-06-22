package com.waelkhelil.sayara_dz.database.model

import androidx.lifecycle.LiveData

data class ModelSearchResult(
    val data: LiveData<Model>,
    val networkErrors: LiveData<String>
)