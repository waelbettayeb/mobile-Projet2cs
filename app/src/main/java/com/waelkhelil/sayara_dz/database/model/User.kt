package com.waelkhelil.sayara_dz.database.model

data class User(val name:String, val photoUrl:String){
    companion object {
        private var sInstance: User? = null
        fun getUser(pName:String, pPhotoUrl:String): User {
            return sInstance
                ?: synchronized(User::class) {
                    val instance = User(
                        pName,
                        pPhotoUrl
                    )
                    sInstance = instance
                    return instance
            }
        }
    }
}