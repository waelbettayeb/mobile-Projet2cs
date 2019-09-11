/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.waelkhelil.sayara_dz.database


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.waelkhelil.sayara_dz.database.data.BrandsRepository
import com.waelkhelil.sayara_dz.view.brand_ui.ModelViewModel
import com.waelkhelil.sayara_dz.view.home_ui.BrandListViewModel
import com.waelkhelil.sayara_dz.model_ui.ModelVersionsViewModel

/**
 * Factory for ViewModels
 */
class ViewModelFactory(private val repository: BrandsRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BrandListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BrandListViewModel() as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

   inner  class VersionViewModelFactory( private val id_modele:String) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ModelViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ModelVersionsViewModel(id_modele) as T
            }

            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}