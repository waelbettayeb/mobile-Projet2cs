package com.waelkhelil.sayara_dz.view.model_ui

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.database.api.SayaraDzService
import com.waelkhelil.sayara_dz.database.data.BrandsRepository
import com.waelkhelil.sayara_dz.database.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class ModelVersionsViewModel (var id_modele:String) : ViewModel() {

    private var versionsList: MutableLiveData<List<Version>>? = null
    private var carsList: MutableLiveData<List<Vehicule>> = MutableLiveData<List<Vehicule>>()
    private var Repository: BrandsRepository? = null
    // LiveData of network errors.
    private var orderMessage = MutableLiveData<String>()
    private var networkErrors = MutableLiveData<String>()


    fun init() {
        if (versionsList != null) {
            return
        }
        Repository = BrandsRepository.instance
        versionsList = Repository!!.getVersions(id_modele, { error ->
            networkErrors!!.postValue(error)})


    }

    fun getRepository(): LiveData<List<Version>>? {
        return versionsList
    }
    fun getNetworkErrors(): LiveData<String>? {
        return networkErrors

    }
    fun getOrderMessage(): LiveData<String>? {
        return orderMessage

    }

    fun getVersionPrice(id_version:String):MutableLiveData<List<VersionPrice>>
    {
        return Repository!!.getVersionPrice(id_version,{error ->
            networkErrors!!.postValue(error)})
    }
    fun getColorPrice(id_color:String): MutableLiveData<List<ColorPrice>>
    {
        return Repository!!.getColorPrice(id_color,{ error ->
            networkErrors!!.postValue(error)})
    }
    fun getOptionPrice(id_option:String): MutableLiveData<List<OptionPrice>>
    {
        return Repository!!.getOptionPrice(id_option,{error ->
            networkErrors!!.postValue(error)})
    }

    fun getVersionOptions(id_version:String):MutableLiveData<List<Option>>
    {
        return Repository!!.getVersionOptions(id_version,{error ->
            networkErrors!!.postValue(error)})
    }








    fun reserver (user_email:String,car_id:String,price:Float)
        {    // pick current system date
            var dt:Date  = Date();

            // set format for date
            var dateFormat :SimpleDateFormat  =  SimpleDateFormat("yyyy-MM-dd");

            // parse it like
            var current_date:String = dateFormat.format(dt);


        val paramObject = JsonObject()
            paramObject.addProperty( "automobiliste", user_email)
            paramObject.addProperty( "vehicule", car_id)
            paramObject.addProperty( "date",current_date)
            paramObject.addProperty( "montant",price)
            paramObject.addProperty(  "reserver", true)
    


             SayaraDzService.create().sendReservation(paramObject).
                enqueue(
                    object : Callback<reservation> {
                        override fun onResponse(call: Call<reservation>, response: Response<reservation>) {
                            Log.i("response color ", "answered")
                            if (response.isSuccessful()) {


                                Log.i("success res responded ", "success")
                            } else {

                                Log.i("fail res  responded", "success")
                                if (response.code() == 400) {
                                    Log.v("Error code 400",response.errorBody()!!.string())
                                }

                            }
                        }
                        override fun onFailure(call: Call<reservation>, t: Throwable) {


                            Log.i("failure", "failed")

                        }





           /* userCall.enqueue(this.)
        } catch (e: JSONException) {
            e.printStackTrace()
        }*/


    })

        }

   /* fun checkAvailable (brand_id:String,modele_id:String,version_id:String,color_id:String,options:List<String>):Int
    {


        var answer:Int=0
        val paramObject = JsonObject()
        val critere = JsonObject()
        var optionsList:JsonArray = JsonArray()
        var it: Iterator<String> = options.listIterator()
        while(it.hasNext()){
        optionsList.add(it.next())}

        paramObject.addProperty( "marque", brand_id)
        paramObject.addProperty( "modele", modele_id)
        paramObject.addProperty( "version",version_id)
        paramObject.addProperty( "couleur",color_id)
        paramObject.add(  "option", optionsList)




         SayaraDzService.create().checkAvailable(paramObject.toString()).
            enqueue(
                object : Callback<List<Vehicule>> {
                    override fun onResponse(call: Call<List<Vehicule>>, response: Response<List<Vehicule>>) {
                        if (response.isSuccessful()) {


                            Log.i("success res responded ", "success")
                            if (response.body()!!.size!=0)
                            {answer = 1


                                reserver ("fm_bourouais@esi.dz", response.body()!!.get(0).car_id, 10.22F)
                            }
                            else
                            {
                                answer= 2
                            }
                        } else {

                            Log.i("fail res  responded", "${paramObject.toString()}")

                                Log.v("Error code ",response.message())


                        }
                    }
                    override fun onFailure(call: Call<List<Vehicule>>, t: Throwable) {


                        Log.i("failure", "failed")

                    }




                })

        return answer

    }*/

     fun checkAvailable (brand_id:String,modele_id:String,version_id:String,color_id:String,options:List<String>,price:Float)
    {
        SayaraDzService.create().checkAvailable().enqueue(
                object : Callback<List<Vehicule>> {
                    override fun onResponse(call: Call<List<Vehicule>>, response: Response<List<Vehicule>>) {
                        if (response.isSuccessful()) {
                            if (response.body()!!.size!=0)
                            {

                                carsList!!.value=response.body()

                                orderMessage.postValue( reserve(brand_id,modele_id,version_id,color_id,options,price))

                            }

                        } else {
                            orderMessage.postValue(Resources.getSystem().getString(R.string.order_error))





                        }
                    }
                    override fun onFailure(call: Call<List<Vehicule>>, t: Throwable) {

                        orderMessage.postValue(Resources.getSystem().getString(R.string.order_error))
                    }




                })



    }

    fun reserve(brand_id:String,modele_id:String,version_id:String,color_id:String,options:List<String>,price:Float):String
    {

        var message:String =""
        var found:Boolean = false
        var i=carsList!!.value!!.size
        var j:Int=0
        var item:Vehicule?
        while (j<i && !found)
        {   item= carsList.value!![j]
            if (( item.brand_id ==brand_id)&&(item.color_id==color_id)&&(item.modele_id==modele_id)
                &&(item.version_id==version_id)

                && optionsEqual(item.options,options))
            {
                reserver("fm_bourouais@esi.dz",item.car_id,price)


                message = "demande envoyée "
                found=true
            }

            j++
        }
        if ( !found)
        {
            message = "Non disponible "
        }

        return message

    }

    fun optionsEqual (carOptions: String,neededOptions : List<String>):Boolean
    {   var isEqual:Boolean = false
        var sum:Int=0
        if (neededOptions.size==0){}
        else{

        for ( item in neededOptions)
        {
            if (carOptions.contains(item))
            {sum++}
        }
        if ( sum==neededOptions.size){isEqual=true}


    }
        return isEqual
    }



}
