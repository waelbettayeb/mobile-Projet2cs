package com.waelkhelil.sayara_dz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.waelkhelil.sayara_dz.database.model.Version


class SharedViewModel : ViewModel() {

    val mCompareList: MutableLiveData<Set<Version>> = MutableLiveData(setOf())
    private val mNewCompareList = mutableSetOf<Version>()

    fun addToCompareList(vararg Version: Version){
        mNewCompareList.addAll(Version)
        mCompareList.postValue(mNewCompareList)
    }
    fun freeVersions(){
        mNewCompareList.clear()
        mCompareList.postValue(mNewCompareList)
    }
}