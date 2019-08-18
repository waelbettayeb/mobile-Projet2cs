package com.waelkhelil.sayara_dz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.waelkhelil.sayara_dz.database.data.BrandsRepository
import com.waelkhelil.sayara_dz.database.model.Version
import com.waelkhelil.sayara_dz.database.model.reservation


class SharedViewModel : ViewModel() {

    val mCompareList: MutableLiveData<Set<Version>> = MutableLiveData(setOf())
    private val mNewCompareList = mutableSetOf<Version>()
    private var Repository: BrandsRepository? = null
    // LiveData of network errors.
    private var networkErrors = MutableLiveData<String>()
    private var userOrdersList: MutableLiveData<List<reservation>>? = null


    fun addToCompareList(vararg Version: Version){
        mNewCompareList.addAll(Version)
        mCompareList.postValue(mNewCompareList)
    }
    fun removeFromCompareList(vararg Version: Version){
        mNewCompareList.removeAll(Version)
        mCompareList.postValue(mNewCompareList)
    }
    fun freeVersions(){
        mNewCompareList.clear()
        mCompareList.postValue(mNewCompareList)
    }

    fun getUserOrders(userEmail:String): LiveData<List<reservation>>? {
        Repository = BrandsRepository.instance
        userOrdersList = Repository!!.getUserOrders(userEmail, { error ->
            networkErrors!!.postValue(error)})

        return userOrdersList

    }
}