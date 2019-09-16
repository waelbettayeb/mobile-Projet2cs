package com.waelkhelil.sayara_dz

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.waelkhelil.sayara_dz.database.data.BrandsRepository
import com.waelkhelil.sayara_dz.database.model.VersionPrice
import com.waelkhelil.sayara_dz.view.model_ui.ModelVersionsViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations


class ModelVersionsViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    //lateinit var id_modele: String
    @Mock
    lateinit var viewModel: ModelVersionsViewModel
    @Mock
    lateinit var repository: BrandsRepository

    @Before
    fun setUp() {
        //id_modele=ArgumentMatchers.anyString()
        MockitoAnnotations.initMocks(this)
        //.viewModel = ModelVersionsViewModel(this.id_modele)
    }

    @Test
    fun getVersionPrice() {
        // Mock API response
        val versionPrice = VersionPrice("100.0", "25-01-2011", "25-02-2011")
        `when`(repository.getVersionPrice("1", {error -> Log.e("erreur",error) }/*ArgumentMatchers.any()*/))
            .thenReturn(MutableLiveData(listOf(versionPrice)))

        viewModel.getVersionPrice("1")
        val inOrder = Mockito.inOrder(viewModel)
        inOrder.verify(viewModel, times(1)).getVersionPrice("1")


        }





    @Test
    fun getVersionOptions() {
    }

    @Test
    fun reserver() {
    }

    @Test
    fun checkAvailable() {
    }
}